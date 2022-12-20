package sk.adr3ez.darkbits.bukkit.events.custom;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class DarkBitsPlayerCurrencyTakeEvent extends Event implements Cancellable, Silent {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private boolean isCancelled;
    private final double amount;
    private boolean silent;

    private String taker;

    public DarkBitsPlayerCurrencyTakeEvent(Player player, String taker, Double amount, Boolean silent) {
        this.player = player;
        this.taker = taker;
        this.amount = amount;
        setSilent(silent);
        isCancelled = false;
    }
    public DarkBitsPlayerCurrencyTakeEvent(Player player, String taker, Double amount) {
        this.player = player;
        this.taker = taker;
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

    public String getTaker() {
        return taker;
    }
}
