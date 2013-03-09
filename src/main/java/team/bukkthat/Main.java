package team.bukkthat;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import team.bukkthat.commands.CommandHandler;
import team.bukkthat.listeners.BlockListener;
import team.bukkthat.listeners.OtherListener;
import team.bukkthat.listeners.PlayerListener;
import team.bukkthat.util.PlayersConfig;

public class Main extends JavaPlugin {

    private FileConfiguration playersConfig;
    private PlayersConfig pc;

    private static final String[] COMMANDS =
    {
        "something",
        "tp",
        "pvpopt",
        "tpa",
        "tpaccept",
        "tpdeny",
    };

    @Override
    public void onEnable() {
        //saveDefaultConfig();
        pc = new PlayersConfig(this);
        pc.reloadPlayers();
        pc.savePlayers();
        playersConfig = pc.getPlayers();

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockListener(this), this);
        getServer().getPluginManager().registerEvents(new OtherListener(this), this);

        for(String command : COMMANDS) {
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

    public PlayersConfig getPc() {
        return pc;
    }

}
