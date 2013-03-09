package team.bukkthat.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import team.bukkthat.Main;

public class TPACommand implements CommandExecutor {

    private final Main plugin;
    private final ChatColor RED = ChatColor.RED;
    private final ChatColor GREEN = ChatColor.GREEN;

    public TPACommand(Main main) {
        this.plugin = main;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player) sender;
            switch (args.length) {
                case 1:
                    final Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        p.sendMessage(this.RED + "That player is not online!");
                        return true;
                    }
                    this.plugin.getTpaRef().put(target.getName(), p.getName());
                    this.plugin.getTpaTimes().put(target.getName(), System.currentTimeMillis());
                    p.sendMessage(this.GREEN + "Request sent!");
                    final int timeout = /*plugin.getConfig().getInt("tp-timeout", 30)*/30;
                    target.sendMessage(ChatColor.GOLD + p.getName() + ChatColor.BLUE + " has sent a tp request to tp to you. You have " + timeout + " seconds until this request times out.");
                    return true;

                default:
                    p.sendMessage(this.RED + "Usage: /<command> <player>");
                    return true;
            }
        } else {
            sender.sendMessage(this.RED + "You need to be a player to do that!");
            return true;
        }
    }
}
