package team.bukkthat.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import team.bukkthat.Main;

public class TPAcceptCommand implements CommandExecutor {

    private final Main plugin;
    private final ChatColor RED = ChatColor.RED;
    private final ChatColor GREEN = ChatColor.GREEN;

    public TPAcceptCommand(Main main) {
        this.plugin = main;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("test");
        for (final String name : this.plugin.getTpaRef().keySet()) {
            sender.sendMessage(name);
        }
        if (sender instanceof Player) {
            final Player p = (Player) sender;
            if (args.length != 0) {
                p.sendMessage(this.RED + "Wrong number of arguments!");
                p.sendMessage(this.RED + "Usage: /<command>");
                return true;
            }
            if (this.plugin.getTpaRef().containsKey(p.getName())) {
                final Player target = Bukkit.getPlayer(this.plugin.getTpaRef().get(p.getName()));
                if (target == null) {
                    p.sendMessage(this.RED + "Sorry, that player has since logged out.");
                    this.plugin.getTpaRef().remove(p.getName());
                    return true;
                }
                final long time = System.currentTimeMillis();
                final Integer timeout = /*plugin.getConfig().getInt("tp-timeout", 30)*/30;
                final long oldTime = this.plugin.getTpaTimes().get(p.getName());
                if ((time - oldTime) < new Long(timeout * 1000)) {
                    p.sendMessage(this.RED + "Request timed out!");
                    return true;
                }
                p.sendMessage(this.GREEN + "Teleporting!");
                p.teleport(target);
                this.plugin.getTpaRef().remove(p.getName());
                this.plugin.getTpaTimes().remove(p.getName());
                return true;
            } else {
                p.sendMessage(this.RED + "You have no pending requests.");
                return true;
            }
        } else {
            sender.sendMessage(this.RED + "You need to be a player to do that!");
            return true;
        }
    }

}
