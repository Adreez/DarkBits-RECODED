package sk.adr3ez.darkbits.bukkit.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import sk.adr3ez.darkbits.bukkit.DarkBits;
import sk.adr3ez.darkbits.bukkit.events.custom.DarkBitsPlayerCurrencyReceiveEvent;
import sk.adr3ez.darkbits.bukkit.sql.SQLGetter;
import sk.adr3ez.darkbits.bukkit.commands.SubCommand;
import sk.adr3ez.darkbits.bukkit.utils.ChatUtils;

import java.util.Arrays;

public class add extends SubCommand {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "Adds a amount of value to players wallet";
    }

    @Override
    public String getPermission() {
        return "darkbits.add";
    }

    @Override
    public String getUsage() {
        return "/darkbits add <player> <value> -s";
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        boolean silent = false;
        if (sender.hasPermission(DarkBits.config.get().getString("Permissions.add"))) {
            if (Arrays.toString(args).contains("-s")) {
                silent = true;
            }
            if (args.length == 3) {
                String targetplayer = args[2];
                if (SQLGetter.data.exists(args[2])) {
                    if (isDouble(args[3])) {
                        Bukkit.getPluginManager().callEvent(new DarkBitsPlayerCurrencyReceiveEvent(
                                Bukkit.getPlayer(targetplayer), Double.parseDouble(args[3]), silent)
                        );
                    } else {
                        sender.sendMessage("Value must be number with or without decimals!");
                    }
                } else {
                    sender.sendMessage("Player doesn't exist!");
                }
            }
        } else {
            sender.sendMessage(new ChatUtils().format(DarkBits.config.get().getString("Messages.noperms")));
        }
    }
}
