package team.bukkthat;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import team.bukkthat.commands.HomeCommand;
import team.bukkthat.commands.PVPOptCommand;
import team.bukkthat.commands.SomethingCommand;
import team.bukkthat.commands.TPACommand;
import team.bukkthat.commands.TPAcceptCommand;
import team.bukkthat.commands.TPCommand;
import team.bukkthat.commands.TPDenyCommand;
import team.bukkthat.listeners.BlockListener;
import team.bukkthat.listeners.OtherListener;
import team.bukkthat.listeners.PlayerListener;
import team.bukkthat.util.PlayersConfig;

public class Main extends JavaPlugin {

    private PlayersConfig playersConfig;

    public HashMap<String, String> tpaRef = new HashMap<String, String>();
    public HashMap<String, Long> tpaTimes = new HashMap<String, Long>();

    public PlayersConfig getPlayersConfig() {
        return this.playersConfig;
    }

    @Override
    public void onEnable() {
        //saveDefaultConfig();
        this.playersConfig = new PlayersConfig(this);

        this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        this.getServer().getPluginManager().registerEvents(new BlockListener(), this);
        this.getServer().getPluginManager().registerEvents(new OtherListener(), this);

        this.getCommand("pvpopt").setExecutor(new PVPOptCommand(this));
        this.getCommand("something").setExecutor(new SomethingCommand());
        this.getCommand("tp").setExecutor(new TPCommand());
        this.getCommand("tpa").setExecutor(new TPACommand(this));
        this.getCommand("tpaccept").setExecutor(new TPAcceptCommand(this));
        this.getCommand("tpdeny").setExecutor(new TPDenyCommand(this));
        this.getCommand("home").setExecutor(new HomeCommand(this));
        this.getCommand("sethome").setExecutor(new HomeCommand(this));

        final ItemStack orb = new ItemStack(Material.EYE_OF_ENDER, 2);
        final ItemMeta im = orb.getItemMeta();
        im.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "TP Orb");
        orb.setItemMeta(im);

        final ShapedRecipe recipe = new ShapedRecipe(orb);
        recipe.shape("ABA", "CDC", "ABA")
        .setIngredient('A', Material.BLAZE_ROD)
        .setIngredient('B', Material.DIAMOND)
        .setIngredient('C', Material.MAGMA_CREAM)
        .setIngredient('D', Material.EYE_OF_ENDER);

        this.getServer().addRecipe(recipe);
    }

}
