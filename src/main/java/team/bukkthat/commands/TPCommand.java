package team.bukkthat.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import team.bukkthat.Main;

public class TPCommand {
    
    private Main plugin;
    private ChatColor GREEN = ChatColor.GREEN;
    private ChatColor RED = ChatColor.RED;
    
    public TPCommand(Main main, CommandHandler commandHandler) {
        //Get the instance of the main plugin, for config use or other things.
        this.plugin = main;
    }

    public boolean execute(CommandSender sender, String[] args) {
        if(sender.hasPermission("server.tp")) {
            switch(args.length) {
                case 1:
                    if(sender instanceof Player) {
                        Player p = (Player) sender;
                        Player target = Bukkit.getPlayer(args[0]);
                        if(target != null) {
                            p.teleport(target);
                            p.sendMessage(GREEN + "Teleported!");
                            return true;
                        } else {
                            p.sendMessage(RED + "That player is not online!");
                            return true;
                        }
                    } else {
                        sender.sendMessage(RED + "Only players can teleport!");
                    }
                case 2:
                    Player from = Bukkit.getPlayer(args[0]);
                    Player to = Bukkit.getPlayer(args[1]);
                    if(from == null) {
                        sender.sendMessage(RED + args[0]+" is not online!");
                        return true;
                    }
                    if(to == null) {
                        sender.sendMessage(RED + args[1] + " is not online!");
                        return true;
                    }
                    from.teleport(to);
                    sender.sendMessage(GREEN + from.getName() + " was teleported to " + to.getName() + "!");
                    return true;
                default:
                    usage(sender);
                    return true;
            }
        } else {
            sender.sendMessage(RED + "You do not have permission to do that!");
        }
        return true;
    }

    private void usage(CommandSender sender) {
        sender.sendMessage(RED + "Usage: /<command> <player>");
        sender.sendMessage(RED + "Usage: /<command> <player> <player>");
    }
    
}
