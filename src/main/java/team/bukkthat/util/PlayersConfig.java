package team.bukkthat.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import team.bukkthat.Main;

public class PlayersConfig {

    private final Main plugin;
    private final File playersFile;
    private FileConfiguration playersConfig;

    public PlayersConfig(Main plugin) {
        this.plugin = plugin;
        this.playersFile = new File(plugin.getDataFolder(), "players.yml");
        this.reloadPlayers();
    }

    public FileConfiguration getPlayers() {
        if (this.playersConfig == null) {
            this.reloadPlayers();
        }
        return this.playersConfig;
    }

    public void reloadPlayers() {
        this.playersConfig = YamlConfiguration.loadConfiguration(this.playersFile);
    }

    public void savePlayers() {
        if (this.playersConfig == null) {
            return;
        }
        try {
            this.getPlayers().save(this.playersFile);
        } catch (final IOException ex) {
            this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.playersConfig, ex);
        }
    }

}
