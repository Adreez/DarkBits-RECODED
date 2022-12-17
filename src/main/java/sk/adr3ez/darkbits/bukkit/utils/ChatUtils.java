package sk.adr3ez.darkbits.bukkit.utils;

import net.md_5.bungee.api.ChatColor;

public class ChatUtils {

    public String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
