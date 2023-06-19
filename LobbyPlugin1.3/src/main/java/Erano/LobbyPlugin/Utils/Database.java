package Erano.LobbyPlugin.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.bukkit.entity.Player;

import Erano.LobbyPlugin.main;
import net.md_5.bungee.api.ChatColor;

public class Database {//hashlemeyi burada yap sadece !
	
	public static boolean isRegistered(UUID uuid) {
	    
	    try (Connection connection = DriverManager.getConnection(getConnectionString(),getUsername(),getPassword())) {
	        String query = "SELECT uuid FROM Players"; // uygun tablo adını ve alan adını kullanın
	        try (PreparedStatement statement = connection.prepareStatement(query);
	             ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                String hashedUUID = resultSet.getString("uuid");
	                if(Hasher.hash(uuid.toString()).equals(hashedUUID)) {
	                	return true;
	                }
	            }
	            return false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false;
	}
 
    public static boolean isLogged(UUID uuid) {
		//veritabanındaki Players tablosundaki last_login ile şuan arasında 0-15 saniye fark var ise true döndür.
		
		LocalDateTime currentDate = LocalDateTime.now(ZoneId.of("GMT+3"));//veritabanına bu şekilde kaydettik ve veritabanında veritipi DATETIME
		try(Connection connection = DriverManager.getConnection(getConnectionString(),getUsername(),getPassword())){
			String query = "Select last_login From Players Where uuid = ?";
			
			try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setString(1, Hasher.hash(uuid.toString()));
	            
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    LocalDateTime lastLogin = resultSet.getTimestamp("last_login").toLocalDateTime();
	                    Duration difference = Duration.between(lastLogin, currentDate);
	                    long seconds = difference.getSeconds();
	                    return seconds >= 0 && seconds <= 15;
	                }
	            }
	        }
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
    public static boolean isCorrectPassword(String enteredPassword,Player player) {
    	
    	try (Connection connection = DriverManager.getConnection(getConnectionString(),getUsername(),getPassword())) {
	        String query = "SELECT password FROM Players"; // uygun tablo adını ve alan adını kullanın
	        try (PreparedStatement statement = connection.prepareStatement(query);
	             ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                String password = resultSet.getString("password");
	                if(Hasher.hash(enteredPassword).equals(password)) {
	                	return true;
	                }
	                else {
	                	player.sendMessage(ChatColor.RED+"Invalid password please try again!");
	                }
	            }
	            return false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
    	
    }
	
	public static String getConnectionString() {
        String host = main.getInstance().getConfig().getString("database.host");
        String port = main.getInstance().getConfig().getString("database.port");
        String database = main.getInstance().getConfig().getString("database.name");
        return "jdbc:mysql://" + host + ":" + port + "/" + database;
    }

    public static String getUsername() {
        return main.getInstance().getConfig().getString("database.username");
    }

    public static String getPassword() {
        return main.getInstance().getConfig().getString("database.password");
    }
    public static void createTableIfNotExists() {
        try (Connection connection = DriverManager.getConnection(getConnectionString(), getUsername(), getPassword())) {
            String query = "CREATE TABLE IF NOT EXISTS Players (" +
                    "uuid VARCHAR(255) NOT NULL, " +
                    "username VARCHAR(255) NOT NULL, " +
                    "password VARCHAR(255) NOT NULL, " +
                    "coin BIGINT, " +
                    "last_login DATETIME, " +
                    "PRIMARY KEY (uuid)" +
                    ")";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Hata durumunda gerekli işlemler yapılabilir
        }
    }
    

}
