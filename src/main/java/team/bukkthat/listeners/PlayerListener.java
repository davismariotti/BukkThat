package team.bukkthat.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import team.bukkthat.Main;

public class PlayerListener implements Listener {

    private final Main plugin;

    public PlayerListener(Main main) {
        this.plugin = main;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        //Make messages colorable
        event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        final Entity entity = event.getEntity();
        final Entity damagerEnt = event.getDamager();
        if ((entity instanceof Player) && (damagerEnt instanceof Player)) {
            final Player player = (Player) entity;
            final Player damager = (Player) damagerEnt;
            if (this.plugin.getPlayersConfig().getPlayers().getBoolean(damager.getName() + ".pvp-opt", false)) {
                damager.sendMessage(ChatColor.RED + "You are opted out of pvp. Use /pvpopt <in/out> to change this status.");
                event.setCancelled(true);
                return;
            }
            if (this.plugin.getPlayersConfig().getPlayers().getBoolean(player.getName() + ".pvp-opt", false)) {
                damager.sendMessage(ChatColor.RED + "That player has pvp disabled!");
                event.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("messages.join", "Welcome <player>!").replaceAll("<player>", event.getPlayer().getName())));
    }

}
