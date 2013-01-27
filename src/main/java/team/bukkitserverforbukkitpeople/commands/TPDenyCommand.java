package team.bukkitserverforbukkitpeople.commands;

import org.bukkit.command.CommandSender;

import team.bukkitserverforbukkitpeople.Main;

public class TPDenyCommand {
    Main plugin;
    CommandHandler ch;
    
    public TPDenyCommand(Main main, CommandHandler commandHandler) {
        this.plugin = main;
        this.ch = commandHandler;
    }
    
    public boolean execute(CommandSender sender, String[] args) {
        //I will add stuff when I have time.
    	return true;
    }
}
