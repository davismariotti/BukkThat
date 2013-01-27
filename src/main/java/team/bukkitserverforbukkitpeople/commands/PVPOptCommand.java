package team.bukkitserverforbukkitpeople.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import team.bukkitserverforbukkitpeople.Main;

public class PVPOptCommand {
    
    Main plugin;
    ChatColor GREEN = ChatColor.GREEN;
    ChatColor RED = ChatColor.RED;
	CommandHandler ch;

    public PVPOptCommand(Main main, CommandHandler commandHandler) {
        this.plugin = main;
        this.ch = commandHandler;
    }

    public boolean execute(CommandSender sender, String[] args) {
    	if(sender instanceof Player) {
    		Player p = (Player) sender;
	    	switch(args.length) {
	    		case 1:
	    			if(args[0].equalsIgnoreCase("in")) {
	    				plugin.pc.getPlayers().set(p.getName()+".pvp-opt", true);
	    				plugin.pc.savePlayers();
	    				p.sendMessage(GREEN+"You now can pvp or be pvp'ed");
	    				return true;
	    			}
	    			else if(args[0].equalsIgnoreCase("out")) {
	    				plugin.pc.getPlayers().set(p.getName()+".pvp-opt", false);
	    				plugin.pc.savePlayers();
	    				p.sendMessage(GREEN+"You no longer can pvp or be pvp'ed");
	    				return true;
	    			}
	    			else {
	    				usage(p);
	    				return true;
	    			}
	    		default:
	    			usage(p);
	    			return true;
	    	}
    	}
    	else {
    		sender.sendMessage(RED+ "You need to be a player to do that!");
    		return true;
    	}
    }

	private void usage(Player p) {
		p.sendMessage(RED + "Usage: /<command> <in/out>");
		
	}
    
}
