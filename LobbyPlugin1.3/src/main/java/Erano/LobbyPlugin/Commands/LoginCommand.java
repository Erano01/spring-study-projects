package Erano.LobbyPlugin.Commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Erano.LobbyPlugin.main;
import Erano.LobbyPlugin.Utils.Database;
import Erano.LobbyPlugin.Utils.Hasher;

public class LoginCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        
        if(Database.isLogged(player.getUniqueId())) {
        	player.sendMessage(ChatColor.GREEN+"You are already logged !");
        	return true;
        }
        
        if(!Database.isRegistered(player.getUniqueId())) {
        	player.sendMessage(ChatColor.RED+"You are not able to login you need to register first !");
        	return true;
        }
		
		String password = args[0];
		
		if(Database.isCorrectPassword(password,player)) {
		
		// MySQL bağlantısı kurma
			try (Connection connection = DriverManager.getConnection(Database.getConnectionString(),Database.getUsername(), Database.getPassword())) {
			    String query = "UPDATE Players SET last_login = ? WHERE uuid = ?";
			    try (PreparedStatement statement = connection.prepareStatement(query)) {
			        LocalDateTime currentDate = LocalDateTime.now(ZoneId.of("GMT+3"));
			        statement.setTimestamp(1, Timestamp.valueOf(currentDate));
			        statement.setString(2, Hasher.hash(player.getUniqueId().toString()));
			        int rowsUpdated = statement.executeUpdate();
	
			        if (rowsUpdated > 0) {
			            player.sendMessage(ChatColor.GREEN + "Login operation Success!");
			        } else {
			            player.sendMessage(ChatColor.RED + "Failed to update last login time.");
			        }
			    }
			} catch (SQLException e) {
		    e.printStackTrace();
		    player.sendMessage(ChatColor.RED + "Login Error");
		    return true;
			}
		}
        return true;
        
	}

	
}
