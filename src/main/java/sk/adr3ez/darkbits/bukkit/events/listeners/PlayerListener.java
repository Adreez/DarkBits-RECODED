package sk.adr3ez.darkbits.bukkit.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import sk.adr3ez.darkbits.bukkit.DarkBits;
import sk.adr3ez.darkbits.bukkit.events.custom.DarkBitsPlayerCurrencyReceiveEvent;
import sk.adr3ez.darkbits.bukkit.events.custom.DarkBitsPlayerCurrencySendEvent;
import sk.adr3ez.darkbits.bukkit.sql.SQLGetter;

public class PlayerListener implements Listener {

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e) {
        if (DarkBits.config.get().getBoolean("Options.createPlayerOnJoin")) {
            SQLGetter.data.createPlayer(e.getPlayer());
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void playerCurrencyReceiveEvent(DarkBitsPlayerCurrencyReceiveEvent e) {
        SQLGetter.data.addToWallet(e.getPlayer().getName(), e.getAmount());
        if (!e.isSilent()) {
            e.getPlayer().sendMessage("+" + e.getAmount());
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void playerCurrencySendEvent(DarkBitsPlayerCurrencySendEvent e) {
        if (SQLGetter.data.getWallet(e.getSender().getName()) >= e.getAmount()) {
            SQLGetter.data.removeFromWallet(e.getSender().getName(), e.getAmount());
            SQLGetter.data.addToWallet(e.getReceiver().getName(), e.getAmount());

            e.getSender().sendMessage("Bits %amount% has been sent to players (%receiver%) wallet!".replaceAll("%amount%", String.valueOf(e.getAmount()))
                    .replaceAll("%receiver%", e.getReceiver().getName()));
            e.getReceiver().sendMessage("Player %sender% has sent you bits (%amount%) wallet!".replaceAll("%amount%", String.valueOf(e.getAmount()))
                    .replaceAll("%sender%", e.getSender().getName()));
        } else {
            e.getSender().sendMessage("Not enough currency in your wallet to send bits!");
        }
    }
}
