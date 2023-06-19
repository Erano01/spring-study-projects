package Erano.LobbyPlugin.Commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Erano.LobbyPlugin.main;
import Erano.LobbyPlugin.Utils.Database;
import Erano.LobbyPlugin.Utils.Hasher;

public class RegisterCommand implements CommandExecutor {

	private Map<Player, String> registrationMap;//for local

    public RegisterCommand() {
        this.registrationMap = new HashMap<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        
        if(Database.isRegistered(player.getUniqueId())) {
        	player.sendMessage(ChatColor.RED+"You are already registered");
        	return true;
        }

        if (args.length != 2) {
            player.sendMessage(ChatColor.RED + "Invalid usage. True format: /register <password> <password>");
            return true;
        }

        String password = args[0];
        String confirmPassword = args[1];


        if (!password.equals(confirmPassword)) {
            player.sendMessage(ChatColor.RED + "Password does not match.");
            return true;
        }
        

        // MySQL bağlantısı kurma
        try (Connection connection = DriverManager.getConnection(Database.getConnectionString(), Database.getUsername(), Database.getPassword())) {
            //username,password,uuid,coin
            String insertQuery = "INSERT INTO players (username, password, uuid, coin, last_login) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            	
            	LocalDateTime date = LocalDateTime.of(2019, 2, 3, 0, 0);//bu kısmın böyle olması gerekmekte güvenlik açığı nedeniyle
                statement.setString(1, player.getName());
                statement.setString(2, Hasher.hash(password));
                statement.setString(3, Hasher.hash(player.getUniqueId().toString()));
                statement.setLong(4, 0);
                statement.setTimestamp(5, Timestamp.valueOf(date));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            player.sendMessage(ChatColor.RED + "Registration Error");
            return true;
        }

        registrationMap.put(player, password);

        player.sendMessage(ChatColor.GREEN + "Registiration Success!");

        return true;
    }


    
}