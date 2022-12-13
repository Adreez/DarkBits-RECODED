package sk.adr3ez.darkbits.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class Files {
    private final JavaPlugin plugin;
    private FileConfiguration dataConfig = null;
    private File configFile = null;
    private final String fileName;

    public Files(JavaPlugin plugin, String filename) {
        this.plugin = plugin;
        fileName = filename;
        if (!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdir();

        //Saves/initialises the config
        saveDefaultConfig();
    }

    public void reloadFiles() {

        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), fileName);
        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = this.plugin.getResource(fileName);
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }

    }

    public FileConfiguration get() {

        if (this.dataConfig == null)
            reloadFiles();
        return this.dataConfig;
    }

    public void saveConfig() {

        //this.plugin.getLogger().log(Level.INFO, "File " + fileName + " has been saved!");
        if (this.dataConfig == null || this.configFile == null) {
            return;
        }

        try {
            this.get().save(this.configFile);
        } catch (IOException e) {
            this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, e);
        }

    }

    public void saveDefaultConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), fileName);
        if (!this.configFile.exists()) {

            this.plugin.saveResource(fileName, false);

        }
    }
}
