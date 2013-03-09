package team.bukkthat.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.entity.Player;

public class SomethingCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Add implementation here
        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.GREEN + "You are a player!");
            return true;
        } else if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(ChatColor.GREEN + "You are a console!");
            return true;
        } else if (sender instanceof BlockCommandSender) {
            sender.sendMessage(ChatColor.GREEN + "You are a block!");
            return true;
        } else if (sender instanceof RemoteConsoleCommandSender) {
            sender.sendMessage(ChatColor.GREEN + "You are a remote console!");
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "What are you?");
            return true;
        }
    }

}
