package team.bukkitserverforbukkitpeople.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import team.bukkitserverforbukkitpeople.Main;

public class TPACommand {
    
    Main plugin;
    CommandHandler ch;
	ChatColor RED = ChatColor.RED;
	ChatColor GREEN = ChatColor.GREEN;
    
    public TPACommand(Main main, CommandHandler commandHandler) {
        this.plugin = main;
        this.ch = commandHandler;
    }

    public boolean execute(CommandSender sender, String[] args) {
    	if(sender instanceof Player) {
    		Player p = (Player) sender;
    		if(p.hasPermission("server.tpa")) {
    			switch(args.length) {
    				case 1:
    					Player target = Bukkit.getPlayer(args[0]);
    					if(target == null) {
							p.sendMessage(RED +"That player is not online!");
    						return true;
    					}
    					ch.tpaRef.put(target.getName(), p.getName());
    					ch.tpaTimes.put(target.getName(), System.currentTimeMillis());
    					p.sendMessage(GREEN + "Request sent!");
    					Integer timeout = /*plugin.getConfig().getInt("tp-timeout", 30)*/30;
    					target.sendMessage(ChatColor.GOLD + p.getName() + ChatColor.BLUE + " has sent a tp request to tp to you. You have " + timeout.toString() + " seconds until this request times out.");
    					return true;
    				
    				default:
    					p.sendMessage(RED + "Usage: /<command> <player>");
    					return true;
    			}
    		}
    		else {
    			p.sendMessage(RED + "You do not have permission to do that");
    			return true;
    		}
    	}
    	else {
    		sender.sendMessage(RED + "You need to be a player to do that!");
    		return true;
    	}
    }
    
}
