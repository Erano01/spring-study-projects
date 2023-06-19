package Erano.LobbyPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import Erano.LobbyPlugin.Commands.LoginCommand;
import Erano.LobbyPlugin.Commands.RegisterCommand;
import Erano.LobbyPlugin.Events.JoinEvent;
import Erano.LobbyPlugin.Events.MoveEvent;
import Erano.LobbyPlugin.Utils.Database;

public class main extends JavaPlugin implements Listener{
	
	public static Map<UUID,BukkitRunnable> playerJoinTimer = new HashMap<>();
	
	private static main instance;
    
    public static main getInstance() {
        return instance;
    }
    public main() {
    	instance = this;
    }
	@Override
	public void onDisable() {
		
	}
	@Override
    public void onEnable() {
		Database.createTableIfNotExists();
        getCommand("register").setExecutor(new RegisterCommand());
        getCommand("login").setExecutor(new LoginCommand());
        getCommand("online").setExecutor(new LoginCommand());
        getServer().getPluginManager().registerEvents(new JoinEvent(),this);
		getServer().getPluginManager().registerEvents(new MoveEvent(), this);
    }

	public Player[] getOnlinePlayers() {
    	ArrayList<Player> online = new ArrayList<>();
		for(Player p : getServer().getOnlinePlayers()) {
			online.add(p);
		}
		return (Player[]) online.toArray(new Player[0]);
	}
    
    
    
}
