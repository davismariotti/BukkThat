package team.bukkitserverforbukkitpeople;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import team.bukkitserverforbukkitpeople.commands.CommandHandler;
import team.bukkitserverforbukkitpeople.listeners.BlockListener;
import team.bukkitserverforbukkitpeople.listeners.OtherListener;
import team.bukkitserverforbukkitpeople.listeners.PlayerListener;

public class Main extends JavaPlugin {

	String[] commands =
			{
			"something", "tp", "tpaccept", "tpdeny"
			};
	
	@Override
	public void onEnable() {
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
		im.setDisplayName(ChatColor.DARK_PURPLE+"TP Orb");
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
