package team.bukkitserverforbukkitpeople;

import org.bukkit.plugin.java.JavaPlugin;

import team.bukkitserverforbukkitpeople.commands.CommandHandler;
import team.bukkitserverforbukkitpeople.listeners.BlockListener;
import team.bukkitserverforbukkitpeople.listeners.OtherListener;
import team.bukkitserverforbukkitpeople.listeners.PlayerListener;

public class Main extends JavaPlugin {

	String[] commands =
			{
			"something"
			};
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		getServer().getPluginManager().registerEvents(new BlockListener(this), this);
		getServer().getPluginManager().registerEvents(new OtherListener(this), this);
		for(String command:commands) {
			getCommand(command).setExecutor(new CommandHandler(this));
		}
	}
	
}
