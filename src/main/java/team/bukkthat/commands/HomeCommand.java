package team.bukkthat.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import team.bukkthat.Main;

public class HomeCommand implements CommandExecutor {

	Main plugin;
	File f;
	
	public YamlConfiguration getHomes() {
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		YamlConfiguration yc = YamlConfiguration.loadConfiguration(f);
		return yc;
	}
	
	public HomeCommand(Main m) {
		this.plugin = m;
		f = new File(plugin.getDataFolder(), "homes.yml");
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cs instanceof Player) {
			Player p = (Player) cs;
			if(cmd.getName().equalsIgnoreCase("home")) {
				YamlConfiguration yc = getHomes();
				String world = yc.getString(p.getName()+".world");
				Double x = yc.getDouble(p.getName()+".x");
				Double y = yc.getDouble(p.getName()+".y");
				Double z = yc.getDouble(p.getName()+".z");
				if(x == null || y == null || z == null || world == null) {
					p.sendMessage(ChatColor.RED+"No Home Set!");
					return true;
				}
				Location l = new Location(Bukkit.getWorld(world), x, y, z);
				p.teleport(l);
				return true;
			}
			else if(cmd.getName().equalsIgnoreCase("sethome")){
				Location l = p.getLocation();
				YamlConfiguration yc = getHomes();
				yc.set(p.getName()+".world", l.getWorld().getName());
				yc.set(p.getName()+".x", l.getBlockX());
				yc.set(p.getName()+".y", l.getBlockY());
				yc.set(p.getName()+".z", l.getBlockZ());
				try {
					yc.save(f);
				} catch (IOException e) {
					e.printStackTrace();
				}
				p.sendMessage(ChatColor.GREEN+"Home Set!");
				return true;
			}
		}
		else {
			cs.sendMessage(ChatColor.RED+"Only a player can do that!");
			return true;
		}
		return true;
	}
	
}
