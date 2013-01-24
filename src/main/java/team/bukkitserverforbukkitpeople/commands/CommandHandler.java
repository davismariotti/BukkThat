package team.bukkitserverforbukkitpeople.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import team.bukkitserverforbukkitpeople.Main;

public class CommandHandler implements CommandExecutor {

	Main plugin;
	
	public CommandHandler(Main main) {
		this.plugin = main;
	}
	
	public boolean onCommand(CommandSender cs, Command cmdObj, String label, String[] args) {
		String cmd = cmdObj.getName();
		if(cmd.equalsIgnoreCase("something")) return new SomethingCommand(plugin).execute(cs, args);
        else if(cmd.equalsIgnoreCase("tp")) return new TPCommand(plugin).execute(cs, args);
        else if(cmd.equalsIgnoreCase("tpa")) return new TPACommand(plugin).execute(cs, args);
        else if(cmd.equalsIgnoreCase("tpaccept")) return new TPAcceptCommand(plugin).execute(cs, args);
        else if(cmd.equalsIgnoreCase("tpdeny")) return new TPDenyCommand(plugin).execute(cs, args);
		return false;
	}

	
	
	
}
