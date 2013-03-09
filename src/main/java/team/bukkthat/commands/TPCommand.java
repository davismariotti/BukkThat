package team.bukkthat.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPCommand implements CommandExecutor {

    private final ChatColor GREEN = ChatColor.GREEN;
    private final ChatColor RED = ChatColor.RED;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args.length) {
            case 1:
                if (sender instanceof Player) {
                    final Player p = (Player) sender;
                    final Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        p.teleport(target);
                        p.sendMessage(this.GREEN + "Teleported!");
                        return true;
                    } else {
                        p.sendMessage(this.RED + "That player is not online!");
                        return true;
                    }
                } else {
                    sender.sendMessage(this.RED + "Only players can teleport!");
                }
            case 2:
                final Player from = Bukkit.getPlayer(args[0]);
                final Player to = Bukkit.getPlayer(args[1]);
                if (from == null) {
                    sender.sendMessage(this.RED + args[0] + " is not online!");
                    return true;
                }
                if (to == null) {
                    sender.sendMessage(this.RED + args[1] + " is not online!");
                    return true;
                }
                from.teleport(to);
                sender.sendMessage(this.GREEN + from.getName() + " was teleported to " + to.getName() + "!");
                return true;
            default:
                this.usage(sender);
                return true;
        }
    }

    private void usage(CommandSender sender) {
        sender.sendMessage(this.RED + "Usage: /<command> <player>");
        sender.sendMessage(this.RED + "Usage: /<command> <player> <player>");
    }

}
