package team.bukkitserverforbukkitpeople.listeners;

import org.bukkit.ChatColor;
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
            event.getPlayer().sendMessage(plugin.getConfig().getString("messages.join", "Welcome <player>!").replaceAll("&", "§").replaceAll("<player>", event.getPlayer().getName()));
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
            //Make messages colorable
            event.setMessage(ChatColor.translateAlternateColorCodes("&".charAt(0), event.getMessage()));
	} 
	
}
