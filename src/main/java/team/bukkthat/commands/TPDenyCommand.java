package team.bukkthat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import team.bukkthat.Main;

public class TPDenyCommand implements CommandExecutor {

	Main plugin;
	
    public TPDenyCommand(Main main) {
		this.plugin = main;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //I will add stuff when I have time.
        return true;
    }

}
