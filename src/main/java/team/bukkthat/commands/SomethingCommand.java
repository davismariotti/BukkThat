package team.bukkthat.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.entity.Player;

import team.bukkthat.Main;

public class SomethingCommand {

    private Main plugin;
    private CommandHandler ch;

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
        } else if(cs instanceof ConsoleCommandSender) {
            cs.sendMessage(ChatColor.GREEN+"You are a console!");
            return true;
        } else if(cs instanceof BlockCommandSender) {
            cs.sendMessage(ChatColor.GREEN+"You are a block!");
            return true;
        } else if(cs instanceof RemoteConsoleCommandSender) {
            cs.sendMessage(ChatColor.GREEN+"You are a remote console!");
            return true;
        } else {
            cs.sendMessage(ChatColor.RED+"What are you?");
            return true;
        }
    }
}
