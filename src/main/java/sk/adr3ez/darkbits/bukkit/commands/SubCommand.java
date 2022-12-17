package sk.adr3ez.darkbits.bukkit.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class SubCommand {

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getPermission();

    public abstract String getUsage();

    public abstract void onCommand(CommandSender sender, String[] args);
}
