package team.bukkitserverforbukkitpeople.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import team.bukkitserverforbukkitpeople.Main;

public class Teleport {
    
    Main plugin;

    public Teleport(Main main) {
        //Get the instance of the main plugin, for config use or other things.
        this.plugin = main;
    }

    public boolean execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission("BukkitServerForPeople.teleport")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command!");
                return true;
            }
        if(sender instanceof Player){
            if(args.length == 1) {
                Player player1 = (Player)sender;
                Player player2 = null;
                try{player2 = Bukkit.getServer().getPlayer(args[0]);}
                catch(Exception e) {
                    sender.sendMessage(ChatColor.RED + "Player not found!");
                    return true;
                }
                if(player2 == null) {
                    sender.sendMessage(ChatColor.RED + "Player not found!");
                    return true;
                }
                if(player1.hasPermission("BukkitServerForPeople.teleport.norequest")) {
                    player1.teleport(player2.getLocation());
                    return true;
                }
                else {
                    player2.setMetadata("TeleportRequestFrom", new FixedMetadataValue(plugin, player1.getName()));
                    player2.sendMessage(ChatColor.BLUE + player1.getName() + "wants to be teleported to you. Type /tpaccept to accept this.");
                    player1.sendMessage(ChatColor.BLUE + "Teleport request sent.");
                    return true;
                }
            }
            else if(args.length == 2) {
                Player player1 = null;
                try{player1 = Bukkit.getServer().getPlayer(args[0]);}
                catch(Exception e) {
                    sender.sendMessage(ChatColor.RED + "Player not found!");
                    return true;
                }
                if(player1 == null) {
                    sender.sendMessage(ChatColor.RED + "Player not found!");
                    return true;
                }
                Player player2 = null;
                try{player2 = Bukkit.getServer().getPlayer(args[1]);}
                catch(Exception e) {
                    sender.sendMessage(ChatColor.RED + "Player not found!");
                    return true;
                }
                if(player2 == null) {
                    sender.sendMessage(ChatColor.RED + "Player not found!");
                    return true;
                }
                if(sender.hasPermission("BukkitServerForPeople.teleport.norequest")) {
                    player1.teleport(player2.getLocation());
                    return true;
                }
                else {
                    player2.setMetadata("TeleportRequestFrom", new FixedMetadataValue(plugin, player1.getName()));
                    player2.sendMessage(ChatColor.BLUE + ((Player)sender).getName() + "wants to teleport" + player1.getName() +" to you. Type /tpaccept to accept this.");
                    player1.sendMessage(ChatColor.BLUE + "Teleport request sent.");
                    return true;
                }
            }
            else {
                sender.sendMessage(ChatColor.RED + "Invalid usage!");
                return false;
            }
        }
        else {
            if(args.length == 2) {
                
                Player player1 = null;
                try{player1 = Bukkit.getServer().getPlayer(args[0]);}
                catch(Exception e) {
                    sender.sendMessage(ChatColor.RED + "Player not found!");
                    return true;
                }
                if(player1 == null) {
                    sender.sendMessage(ChatColor.RED + "Player not found!");
                    return true;
                }
                Player player2 = null;
                try{player2 = Bukkit.getServer().getPlayer(args[1]);}
                catch(Exception e) {
                    sender.sendMessage(ChatColor.RED + "Player not found!");
                    return true;
                }
                if(player2 == null) {
                    sender.sendMessage(ChatColor.RED + "Player not found!");
                    return true;
                }
            }
            else {
                sender.sendMessage(ChatColor.RED + "Invalid usage!");
                return false;
            }
        }
        return true;
    }
    
}
