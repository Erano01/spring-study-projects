package Erano.LobbyPlugin.Events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import Erano.LobbyPlugin.main;
import net.md_5.bungee.api.ChatColor;

public class MoveEvent implements Listener{
	
	
	@EventHandler
	public void move(PlayerMoveEvent event) {
		if(main.playerJoinTimer.containsKey(event.getPlayer().getUniqueId())) {
			event.setCancelled(true);
		}	
	}
}
