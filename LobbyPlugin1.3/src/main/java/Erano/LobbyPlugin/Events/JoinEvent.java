package Erano.LobbyPlugin.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.checkerframework.framework.qual.DefaultQualifier.List;

import Erano.LobbyPlugin.main;
import Erano.LobbyPlugin.Tasks.Timer;


public class JoinEvent implements Listener{
	
	@EventHandler()
	public void onJoin(PlayerJoinEvent e) {
		
		main.playerJoinTimer.put(e.getPlayer().getUniqueId(), new Timer(30,e.getPlayer()));
		
		
		
	}
	
	
}
