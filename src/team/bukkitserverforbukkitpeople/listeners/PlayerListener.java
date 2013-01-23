package team.bukkitserverforbukkitpeople.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import team.bukkitserverforbukkitpeople.Main;

public class PlayerListener implements Listener {

	Main plugin;
	
	public PlayerListener(Main main) {
		this.plugin = main;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		//Send them a message?
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		//Make messages colorable
		event.setMessage(ChatColor.translateAlternateColorCodes("&".charAt(0), event.getMessage()));
	} 
	
}
