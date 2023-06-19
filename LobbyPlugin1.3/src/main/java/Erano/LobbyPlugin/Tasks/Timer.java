package Erano.LobbyPlugin.Tasks;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.scheduler.BukkitRunnable;

import Erano.LobbyPlugin.main;
import Erano.LobbyPlugin.Utils.Database;
import net.md_5.bungee.api.ChatColor;

public class Timer extends BukkitRunnable{
	
	private static int max_seconds;

    private static int now_seconds;
    
    private final main plugin = main.getInstance();
    
    private Player player;

    public Timer(final int max_seconds,Player player) {
        this.max_seconds = max_seconds;
        this.now_seconds = max_seconds;
        this.player=player;
        start();
    }

    public void setPlayer(Player player) {
    	this.player = player;
    }

	public Player getPlayer() {
		return player;
	}


	public void start() {
        //this.runTaskTimer(this.plugin, 0, 20L);
        this.runTaskTimerAsynchronously(this.plugin, 0, 20L);
    }
    
	public void stop() {
        UUID uuid = player.getUniqueId();
        if(main.playerJoinTimer.containsKey(uuid)) {
        	main.playerJoinTimer.remove(uuid);
        }
        this.cancel();
        
    }
	
	@Override
    public void run() {
		if (now_seconds <= 0) {
            // Zaman aşımına uğradı, oyuncuyu sunucudan atabilirsiniz
            player.kickPlayer("Time Out");
            this.cancel();
            return;
        }
		if(Database.isRegistered(this.player.getUniqueId())) {//eğer kayıtlı ise
			
			if(Database.isLogged(this.player.getUniqueId())) {//giriş yapıldıysa sonlandır
				stop();
			}else {
				if(now_seconds%3==0) {
					player.sendMessage(ChatColor.GREEN+"Please use /login <registered_password> to login!");
				}
			}
		}else {
			if(now_seconds%3==0) {
					player.sendMessage(ChatColor.BLUE+"Please use /register <password_example> <password_example> to register!");
				}
			
		}
        now_seconds--;
    }

	
	
	
	

}
