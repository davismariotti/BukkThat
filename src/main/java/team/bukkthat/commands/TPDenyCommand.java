package team.bukkthat.commands;

import org.bukkit.command.CommandSender;

import team.bukkthat.Main;

public class TPDenyCommand {

    private Main plugin;
    private CommandHandler ch;
    
    public TPDenyCommand(Main main, CommandHandler commandHandler) {
        this.plugin = main;
        this.ch = commandHandler;
    }
    
    public boolean execute(CommandSender sender, String[] args) {
        //I will add stuff when I have time.
        return true;
    }
}
