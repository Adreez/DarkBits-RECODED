package sk.adr3ez.darkbits.bukkit.events.custom;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class DarkBitsPlayerCurrencySendEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();

    private final Player sender;
    private final Player receiver;
    private boolean isCancelled;
    private final double amount;

    public DarkBitsPlayerCurrencySendEvent(Player sender, Player receiver, Double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        isCancelled = false;
    }

    public double getAmount() {
        return amount;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

    public Player getSender() {
        return sender;
    }

    public Player getReceiver() {
        return receiver;
    }
}
