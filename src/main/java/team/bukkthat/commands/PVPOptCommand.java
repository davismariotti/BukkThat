package team.bukkthat.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import team.bukkthat.Main;

public class PVPOptCommand implements CommandExecutor {

    private final Main plugin;
    private final ChatColor GREEN = ChatColor.GREEN;
    private final ChatColor RED = ChatColor.RED;

    public PVPOptCommand(Main main) {
        this.plugin = main;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;
            switch (args.length) {
                case 1:
                    if (args[0].equalsIgnoreCase("in")) {
                        this.plugin.getPlayersConfig().getPlayers().set(player.getName() + ".pvp-opt", true);
                        this.plugin.getPlayersConfig().savePlayers();
                        player.sendMessage(this.GREEN + "You now can pvp or be pvp'ed");
                        return true;
                    } else if (args[0].equalsIgnoreCase("out")) {
                        this.plugin.getPlayersConfig().getPlayers().set(player.getName() + ".pvp-opt", false);
                        this.plugin.getPlayersConfig().savePlayers();
                        player.sendMessage(this.GREEN + "You no longer can pvp or be pvp'ed");
                        return true;
                    } else {
                        this.usage(player);
                        return true;
                    }
                default:
                    this.usage(player);
                    return true;
            }
        } else {
            sender.sendMessage(this.RED + "You need to be a player to do that!");
            return true;
        }
    }

    private void usage(Player player) {
        player.sendMessage(this.RED + "Usage: /<command> <in/out>");
    }

}
