package team.bukkthat.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import team.bukkthat.Main;

public class PVPOptCommand {

    private Main plugin;
    private ChatColor GREEN = ChatColor.GREEN;
    private ChatColor RED = ChatColor.RED;
    private CommandHandler ch;

    public PVPOptCommand(Main main, CommandHandler commandHandler) {
        this.plugin = main;
        this.ch = commandHandler;
    }

    public boolean execute(CommandSender sender, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            switch(args.length) {
                case 1:
                    if(args[0].equalsIgnoreCase("in")) {
                        plugin.getPc().getPlayers().set(p.getName()+".pvp-opt", true);
                        plugin.getPc().savePlayers();
                        p.sendMessage(GREEN+"You now can pvp or be pvp'ed");
                        return true;
                    } else if(args[0].equalsIgnoreCase("out")) {
                        plugin.getPc().getPlayers().set(p.getName()+".pvp-opt", false);
                        plugin.getPc().savePlayers();
                        p.sendMessage(GREEN+"You no longer can pvp or be pvp'ed");
                        return true;
                    } else {
                        usage(p);
                        return true;
                    }
                default:
                    usage(p);
                    return true;
                }
        } else {
            sender.sendMessage(RED+ "You need to be a player to do that!");
            return true;
        }
    }

    private void usage(Player p) {
        p.sendMessage(RED + "Usage: /<command> <in/out>");
    }
}
