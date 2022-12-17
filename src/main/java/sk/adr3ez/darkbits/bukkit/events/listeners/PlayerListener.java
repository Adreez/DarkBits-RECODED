package sk.adr3ez.darkbits.bukkit.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import sk.adr3ez.darkbits.bukkit.DarkBits;
import sk.adr3ez.darkbits.bukkit.sql.SQLGetter;

public class PlayerListener implements Listener {

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e) {
        if (DarkBits.config.get().getBoolean("Options.createPlayerOnJoin")) {
            SQLGetter.data.createPlayer(e.getPlayer());
        }
    }
}
