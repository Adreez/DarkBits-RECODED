package sk.adr3ez.darkbits.custom;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class DarkBitsPlayerCurrencyReceiveEvent extends Event implements Cancellable, Silent {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private boolean isCancelled;
    private final double amount;
    private boolean silent;

    public DarkBitsPlayerCurrencyReceiveEvent(Player player, Double amount, Boolean silent) {
        this.player = player;
        this.amount = amount;
        setSilent(silent);
        isCancelled = false;
    }
    public DarkBitsPlayerCurrencyReceiveEvent(Player player, Double amount) {
        this.player = player;
        this.amount = amount;
        silent = false;
        isCancelled = false;
    }

    public double getAmount() {
        return amount;
    }
    public Player getPlayer() {
        return this.player;
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
    public boolean isSilent() {
        return this.silent;
    }

    @Override
    public void setSilent(boolean silent) {
        this.silent = silent;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }
}
