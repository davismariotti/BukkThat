package team.bukkthat.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import team.bukkthat.Main;

public class TPAcceptCommand {

    private Main plugin;
    private CommandHandler ch;
    private ChatColor RED = ChatColor.RED;
    private ChatColor GREEN = ChatColor.GREEN;

    public TPAcceptCommand(Main main, CommandHandler commandHandler) {
        this.plugin = main;
        this.ch = commandHandler;
    }
    
    public boolean execute(CommandSender sender, String[] args) {
        sender.sendMessage("test");
        for(String name : ch.getTpaRef().keySet()) {
            sender.sendMessage(name);
        }
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("server.tpaccept")) {
                if(args.length != 0) {
                    p.sendMessage(RED + "Wrong number of arguments!");
                    p.sendMessage(RED + "Usage: /<command>");
                    return true;
                }
                if(ch.getTpaRef().containsKey(p.getName())) {
                    Player target = Bukkit.getPlayer(ch.getTpaRef().get(p.getName()));
                    if(target == null) {
                        p.sendMessage(RED + "Sorry, that player has since logged out.");
                        ch.getTpaRef().remove(p.getName());
                        return true;
                    }
                    long time = System.currentTimeMillis();
                    Integer timeout = /*plugin.getConfig().getInt("tp-timeout", 30)*/30;
                    long oldTime = ch.getTpaTimes().get(p.getName());
                    if(time - oldTime < new Long(timeout * 1000)) {
                        p.sendMessage(RED + "Request timed out!");
                        return true;
                    }
                    p.sendMessage(GREEN + "Teleporting!");
                    p.teleport(target);
                    ch.getTpaRef().remove(p.getName());
                    ch.getTpaTimes().remove(p.getName());
                    return true;
                } else {
                    p.sendMessage(RED + "You have no pending requests.");
                    return true;
                }
            }
            else {
                p.sendMessage(RED + "You do not have permission to do that!");
                return true;
            }
        }
        else {
            sender.sendMessage(RED + "You need to be a player to do that!");
            return true;
        }
    }
}
