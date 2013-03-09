package team.bukkthat.commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import team.bukkthat.Main;

public class CommandHandler implements CommandExecutor {

    private Main plugin;
    private HashMap<String, String> tpaRef = new HashMap<String, String>();
    private HashMap<String, Long> tpaTimes = new HashMap<String, Long>();

    public CommandHandler(Main main) {
        this.plugin = main;
    }

    public boolean onCommand(CommandSender cs, Command cmdObj, String label, String[] args) {
        String cmd = cmdObj.getName();
        if(cmd.equalsIgnoreCase("something")) return new SomethingCommand(plugin, this).execute(cs, args);
        else if(cmd.equalsIgnoreCase("tp")) return new TPCommand(plugin, this).execute(cs, args);
        else if(cmd.equalsIgnoreCase("tpa")) return new TPACommand(plugin, this).execute(cs, args);
        else if(cmd.equalsIgnoreCase("tpaccept")) return new TPAcceptCommand(plugin, this).execute(cs, args);
        else if(cmd.equalsIgnoreCase("tpdeny")) return new TPDenyCommand(plugin, this).execute(cs, args);
        else if(cmd.equalsIgnoreCase("pvpopt")) return new PVPOptCommand(plugin, this).execute(cs, args);
        return false;
    }

    public HashMap<String, String> getTpaRef() {
            return tpaRef;
    }

    public HashMap<String, Long> getTpaTimes() {
        return tpaTimes;
    }
}
