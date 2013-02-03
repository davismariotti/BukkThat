package team.bukkitserverforbukkitpeople.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import team.bukkitserverforbukkitpeople.Main;

public class TPAcceptCommand {
    Main plugin;
    CommandHandler ch;
	ChatColor RED = ChatColor.RED;
	ChatColor GREEN = ChatColor.GREEN;

    public TPAcceptCommand(Main main, CommandHandler commandHandler) {
        this.plugin = main;
        this.ch = commandHandler;
    }
    
    public boolean execute(CommandSender sender, String[] args) {
    	sender.sendMessage("test");
    	for(String name:ch.tpaRef.keySet()) {
    		sender.sendMessage(name);
    	}
        if(sender instanceof Player) {
        	Player p = (Player) sender;
        	if(p.hasPermission("server.tpaccept")) {
        		if(args.length != 0) {
        			p.sendMessage(RED + "Wrong number of arguments!");
        			p.sendMessage(RED + "Usage: /<command>");
        			return true;
        		}
        		if(ch.tpaRef.containsKey(p.getName())) {
        			Player target = Bukkit.getPlayer(ch.tpaRef.get(p.getName()));
        			if(target == null) {
        				p.sendMessage(RED + "Sorry, that player has since logged out.");
        				ch.tpaRef.remove(p.getName());
        				return true;
        			}
        			long time = System.currentTimeMillis();
        			Integer timeout = /*plugin.getConfig().getInt("tp-timeout", 30)*/30;
        			long oldTime = ch.tpaTimes.get(p.getName());
        			if(time - oldTime < new Long(timeout * 1000)) {
        				p.sendMessage(RED + "Request timed out!");
        				return true;
        			}
        			p.sendMessage(GREEN + "Teleporting!");
        			p.teleport(target);
        			ch.tpaRef.remove(p.getName());
        			ch.tpaTimes.remove(p.getName());
        			return true;
        		}
        		else {
        			p.sendMessage(RED + "You have no pending requests.");
        			return true;
        		}
        	}
        	else {
        		p.sendMessage(RED + "You do not have permission to do that!");
        		return true;
        	}
        }
        else {
        	sender.sendMessage(RED + "You need to be a player to do that!");
        	return true;
        }
    }
}
