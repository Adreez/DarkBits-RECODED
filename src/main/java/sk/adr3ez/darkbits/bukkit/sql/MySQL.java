package sk.adr3ez.darkbits.bukkit.sql;

import org.bukkit.plugin.java.JavaPlugin;
import sk.adr3ez.darkbits.bukkit.DarkBits;
import sk.adr3ez.darkbits.bukkit.utils.ChatUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    public MySQL(JavaPlugin plugin) {
        try {
            connect();
        } catch (SQLException e) {
            plugin.getLogger().warning(new ChatUtils().format("&cError occured while trying to connect to database server! " +
                    "Use /darkbits reload to try the connection"));
        }
    }

    private Connection connection;

    public boolean isConnected() {
        return (connection != null);
    }

    public void connect() throws SQLException {
        if (!isConnected()) {
            String password = DarkBits.config.get().getString("MySQL.password");
            boolean useSSL = DarkBits.config.get().getBoolean("MySQL.useSSL");
            String database = DarkBits.config.get().getString("MySQL.database");
            String username = DarkBits.config.get().getString("MySQL.username");
            String port = DarkBits.config.get().getString("MySQL.port");
            String host = DarkBits.config.get().getString("MySQL.host");
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=" + useSSL, username, password);
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public java.sql.Connection getConnection() {
        return connection;
    }

}
