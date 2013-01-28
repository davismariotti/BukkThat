Bukkit Server For Bukkit People
===========================

If you would like to contribute, make a Pull Request and it may be accepted.

How to Get Your PR Pulled
===========================
There are example commands up so you can learn how we will do it. Look below for more info:

To add a command "something":

Add the command to the commands list in the Main file.
Then add the command to the CommandHandler by doing this:
```java
if(cmd.equalsIgnoreCase("something")) return new SomethingCommand(plugin).execute(cs, args);
```
Then add the new class, something like this:
```java
public class SomethingCommand {
	Main plugin;
        CommandHandler ch;
	public SomethingCommand(Main main, CommandHandler commandHandler) {
		//Get the instance of the main plugin, for config use or other things.
		this.plugin = main;
                this.ch = commandHandler;
	}

	public boolean execute(CommandSender cs, String[] args) {
		//Add implementation here
		if(cs instanceof Player) {
		Player p = (Player) cs;
			p.sendMessage(ChatColor.GREEN+"You are a player!");
			return true;
		}
		else {
			cs.sendMessage(ChatColor.RED+"You are not a player D:");
			return true;
		}
	}

}
```
To use the player's config, use the below code for setting:  
```java
plugin.pc.getPlayers().set(String key, Object o);  
```
And to get:  
```java
plugin.pc.getPlayers().getString(String key, String def);  
plugin.pc.getPlayers().getInt(String key);  
```
Add events like normal, in the respective class, for example, PlayerJoinEvent goes into the PlayerListener class.  
Now you too can help!


Contributors
===========================
gomeow - Code lead  
MrBluebear3 - Other important person  
mncat77 - Converted us to maven  
evilmidget38 - Didn't really help
