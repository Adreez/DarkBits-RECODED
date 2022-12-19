package sk.adr3ez.darkbits.bukkit.sql;

import org.bukkit.entity.Player;
import sk.adr3ez.darkbits.bukkit.DarkBits;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerData {

    String table;
    public PlayerData(String table) {
        this.table = table;
        createTable();
    }

    public void createTable() {
        PreparedStatement ps;
        try {
            ps = DarkBits.mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS " + table + "(nick VARCHAR(100), uuid VARCHAR(100), wallet DOUBLE, PRIMARY KEY (nick))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(Player player) {
        try {
            UUID uuid = player.getUniqueId();
            if (!exists(player.getName())) {
                PreparedStatement ps = DarkBits.mySQL.getConnection().prepareStatement("INSERT INTO " + table + "(nick,uuid,wallet) VALUES (?,?,?)");
                ps.setString(1, player.getName());
                ps.setString(2, uuid.toString());
                ps.setDouble(3, 0);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(String nick) {
        try {
            PreparedStatement ps = DarkBits.mySQL.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE nick=?");
            ps.setString(1, nick);
            ResultSet results = ps.executeQuery();
            return results.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Double getWallet(String nick) {
        try {
            PreparedStatement ps = DarkBits.mySQL.getConnection().prepareStatement("SELECT wallet FROM " + table + " WHERE nick=?");
            ps.setString(1, nick);
            ResultSet rs = ps.executeQuery();
            double wallet;
            if (rs.next()) {
                wallet = rs.getDouble("wallet");
                return wallet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setWallet(String nick, double bits) {
        try {
            PreparedStatement ps = DarkBits.mySQL.getConnection().prepareStatement("UPDATE " + table + " SET wallet=? WHERE nick=?");
            ps.setDouble(1, bits);
            ps.setString(2, nick);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addToWallet(String nick, double bits) {
        try {
            PreparedStatement ps = DarkBits.mySQL.getConnection().prepareStatement("UPDATE " + table + " SET wallet=? WHERE nick=?");
            ps.setDouble(1, getWallet(nick) + bits);
            ps.setString(2, nick);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeFromWallet(String nick, double bits) {
        try {
            PreparedStatement ps = DarkBits.mySQL.getConnection().prepareStatement("UPDATE " + table + " SET wallet=? WHERE nick=?");
            ps.setDouble(1, (getWallet(nick) - bits));
            ps.setString(2, nick);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
