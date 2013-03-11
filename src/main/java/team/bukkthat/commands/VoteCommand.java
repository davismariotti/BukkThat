package team.bukkthat.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import team.bukkthat.Main;
import team.bukkthat.util.VoteInfo;

public class VoteCommand implements CommandExecutor {

	Main plugin;
	Connection c;

	public void startConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.c = DriverManager.getConnection(
					"jdbc:mysql://gomeow.info:3307/Website", plugin.getConfig().getString("sql-user"), plugin.getConfig().getString("sql-pass"));
			return;
		} catch (SQLException e) {
			System.out.println("Could not connect to MySQL server! because: "
					+ e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver not found!");
		}
		return;
	} 

	public void getVotes(CommandSender cs, int page) {
		try {
			SortedMap<Integer, VoteInfo> map = new TreeMap<Integer, VoteInfo>(Collections.reverseOrder());
			PreparedStatement ps = c.prepareStatement("SELECT * FROM `Waiting` WHERE `Activated` = 1 AND `Approved` = 0");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String name = rs.getString("Name");
				int posts = rs.getInt("Posts");
				int plugins = rs.getInt("Plugins");
				int votes = rs.getInt("Votes");
				int points = posts + (plugins*100) + (votes*200);
				map.put(points, new VoteInfo(name, votes));
			}
			cs.sendMessage(ChatColor.BLUE + "Votees: Page ("+String.valueOf(page)+"/"+((map.size()%5==0)?map.size()/5:map.size()/5+1
					)+")");
			int i = 0, k = 0;
			page--;
			for(Entry<Integer,VoteInfo> e:map.entrySet()) {
				k++;
				if(page*5+i+1 == k && k != page*5+6) {
					i++;
					cs.sendMessage(ChatColor.GOLD+e.getValue().getName()+ChatColor.BLUE+" - "+ChatColor.GOLD+e.getValue().getVotes()+" votes");
				}
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public VoteCommand(Main m) {
		this.plugin = m;
		this.startConnection();
	}

	public boolean onCommand(CommandSender cs, Command cmd, String label,
			String[] args) {
		switch(args.length) {
		case 0:
			this.getVotes(cs, 1);
			break;
		case 1:
			try {
				this.getVotes(cs, Integer.parseInt(args[0]));
			}
			catch(NumberFormatException nfe) {
				cs.sendMessage(ChatColor.RED+"The page must be a number!");
			}
			break;
		default:
			cs.sendMessage(ChatColor.RED+"Usage: /vote <page>");
		}
		return true;
	}
}
