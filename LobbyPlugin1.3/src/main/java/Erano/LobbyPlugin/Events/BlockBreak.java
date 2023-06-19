package Erano.LobbyPlugin.Events;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

public class BlockBreak implements Listener{
	
	@EventHandler
    public void breakBlock(BlockBreakEvent e) {
    	Player player = e.getPlayer();
    	if(!player.hasPermission("Lobby.Admin") || !player.hasPermission("Lobby.Designer")) {
    		e.setCancelled(true);
    		player.sendMessage(ChatColor.RED+"Block Kirma Iznin Yok");
    	}
		
    }

}

