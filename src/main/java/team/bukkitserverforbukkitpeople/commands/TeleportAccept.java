package team.bukkitserverforbukkitpeople.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import team.bukkitserverforbukkitpeople.Main;

public class TeleportAccept {
    Main plugin;

    public TeleportAccept(Main main) {
        //Get the instance of the main plugin, for config use or other things.
        this.plugin = main;
    }
    
    public boolean execute(CommandSender sender, String[] args) {
        if(sender instanceof Player) {
            if(args.length > 0) {
                sender.sendMessage(ChatColor.RED + "Invalid usage.");
                return false;
            }
            else {
                Player player = (Player)sender;
                Player player2 = null;
                try{player2 = Bukkit.getServer().getPlayer(player.getMetadata("TeleportRequestFrom").toString());}
                catch(Exception e) {
                    player.sendMessage(ChatColor.RED + "No request was sent.");
                    return true;
                }
                if(player2 == null) {
                    player.sendMessage(ChatColor.RED + "No request was sent.");
                    return true;
                }
                player2.teleport(player.getLocation());
                player.setMetadata("TeleportRequestFrom", new FixedMetadataValue(plugin, "§"));
                return true;
            }
        }
        else {
            sender.sendMessage(ChatColor.RED + "No console usage!");
            return true;
        }
    }
}
