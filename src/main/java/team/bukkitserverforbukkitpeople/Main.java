package team.bukkitserverforbukkitpeople;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import team.bukkitserverforbukkitpeople.commands.CommandHandler;
import team.bukkitserverforbukkitpeople.listeners.BlockListener;
import team.bukkitserverforbukkitpeople.listeners.OtherListener;
import team.bukkitserverforbukkitpeople.listeners.PlayerListener;
import team.bukkitserverforbukkitpeople.util.PlayersConfig;

public class Main extends JavaPlugin {

	public FileConfiguration playersConfig;
	public PlayersConfig pc;
	
	
	
	String[] commands =
			{
			"something", "tp", "pvpopt","tpa", "tpaccept", "tpdeny"
			};
	
	@Override
	public void onEnable() {
		//saveDefaultConfig();
		pc = new PlayersConfig(this);
		pc.reloadPlayers();
		pc.savePlayers();
		playersConfig = pc.playersConfig;
		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		getServer().getPluginManager().registerEvents(new BlockListener(this), this);
		getServer().getPluginManager().registerEvents(new OtherListener(this), this);
		for(String command:commands) {
			//TODO: Fix NPE on this line
			getCommand(command).setExecutor(new CommandHandler(this));
		}
		addRecipes();
	}
	
	public void addRecipes() {
		addTpOrb();
	}
	
	public void addTpOrb() {
		ItemStack orb = new ItemStack(Material.EYE_OF_ENDER, 2);
		ItemMeta im = orb.getItemMeta();
		im.setDisplayName(ChatColor.LIGHT_PURPLE+""+ChatColor.ITALIC+"TP Orb");
		orb.setItemMeta(im);
		ShapedRecipe recipe = new ShapedRecipe(orb);
		recipe.shape("ABA", "CDC", "ABA")
			.setIngredient('A', Material.BLAZE_ROD)
			.setIngredient('B', Material.DIAMOND)
			.setIngredient('C', Material.MAGMA_CREAM)
			.setIngredient('D', Material.EYE_OF_ENDER);
		getServer().addRecipe(recipe);
	}
	
}
