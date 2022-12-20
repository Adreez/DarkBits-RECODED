package sk.adr3ez.darkbits.bukkit.utils;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import sk.adr3ez.darkbits.bukkit.DarkBits;
import sk.adr3ez.darkbits.bukkit.sql.SQLGetter;

public class PAPIExpansion extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "darkbits";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Adr3ez_";
    }

    @Override
    public @NotNull String getVersion() {
        return "${version}";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("bits")) {
            return player == null ? null : String.valueOf(SQLGetter.data.getWallet(player.getName())); // "name" requires the player to be valid
        }

        return null; // Placeholder is unknown by the Expansion
    }

}
