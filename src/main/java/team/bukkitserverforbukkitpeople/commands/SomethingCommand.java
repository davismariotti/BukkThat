package team.bukkitserverforbukkitpeople.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import team.bukkitserverforbukkitpeople.Main;

public class SomethingCommand {

	Main plugin;
	CommandHandler ch;
	
	public SomethingCommand(Main main, CommandHandler commandHandler) {

		this.plugin = main;
		this.ch = commandHandler;
	}

	public boolean execute(CommandSender cs, String[] args) {
		//Add implementation here
		if(cs instanceof Player) {
			Player p = (Player) cs;
			p.sendMessage(ChatColor.GREEN+"You are a player!");
			return true;
		}
		else {
			cs.sendMessage(ChatColor.RED+"You are not a player D:");
			return true;
		}
	}


}
