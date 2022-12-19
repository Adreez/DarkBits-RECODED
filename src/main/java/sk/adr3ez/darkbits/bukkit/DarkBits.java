package sk.adr3ez.darkbits.bukkit;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import sk.adr3ez.darkbits.bukkit.commands.CommandManager;
import sk.adr3ez.darkbits.bukkit.events.ListenerManager;
import sk.adr3ez.darkbits.bukkit.sql.MySQL;
import sk.adr3ez.darkbits.bukkit.sql.SQLGetter;
import sk.adr3ez.darkbits.bukkit.utils.Files;

import java.util.Date;
import java.util.logging.Level;

public final class DarkBits extends JavaPlugin {

    public static Files config;
    public static MySQL mySQL;
    public static SQLGetter sqlGetter;
    @Override
    public void onEnable() {
        long startupStartTime = new Date().getTime();
        getLogger().log(Level.INFO, ChatColor.translateAlternateColorCodes('&',"&aStarting plugin..."));

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getLogger().log(Level.INFO, ChatColor.translateAlternateColorCodes('&',
                    "&6Please consider adding plugin PlaceholderAPI to the server for" +
                    " better experience, you can find it at: https://www.spigotmc.org/resources/placeholderapi.6245/"));
        }

        config = new Files(this, "config.yml");
        mySQL = new MySQL(this);
        sqlGetter = new SQLGetter();
        //Register all listeners
        new ListenerManager(this);
        getCommand("darkbits").setExecutor(new CommandManager(this));

        getLogger().log(Level.INFO, ChatColor.translateAlternateColorCodes('&', "&aPlugin has been loaded in: "
                + (new Date().getTime() - startupStartTime) + " milliseconds"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (mySQL.isConnected()) {
            mySQL.disconnect();
        }
    }

}
