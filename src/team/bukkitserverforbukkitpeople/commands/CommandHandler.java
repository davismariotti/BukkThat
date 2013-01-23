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
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmdObj, String label, String[] args) {
		String cmd = cmdObj.getName();
		if(cmd.equalsIgnoreCase("something")) {
			SomethingCommand command = new SomethingCommand(plugin);
			return command.execute(cs, args);
				
		}
		
		return false;
	}

	
	
	
}
