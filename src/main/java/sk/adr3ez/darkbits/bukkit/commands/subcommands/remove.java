package sk.adr3ez.darkbits.bukkit.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import sk.adr3ez.darkbits.bukkit.DarkBits;
import sk.adr3ez.darkbits.bukkit.commands.SubCommand;

import sk.adr3ez.darkbits.bukkit.events.custom.DarkBitsPlayerCurrencyTakeEvent;
import sk.adr3ez.darkbits.bukkit.sql.SQLGetter;
import sk.adr3ez.darkbits.bukkit.utils.ChatUtils;
import sk.adr3ez.darkbits.bukkit.utils.Parsers;

public class remove extends SubCommand {
    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return "Remove bits from wallet";
    }

    @Override
    public String getUsage() {
        return "/darkbits remove <player> <amount> [silent]";
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (sender.hasPermission("darkbits.remove")) {
            if (DarkBits.mySQL.isConnected()) {
                if (args.length < 3 || args.length > 4) {
                    sender.sendMessage(getUsage());
                } else if (args.length == 3) {
                    if (SQLGetter.data.exists(args[1])) {
                        if (Parsers.isDouble(args[2])) {
                            Bukkit.getPluginManager().callEvent(new DarkBitsPlayerCurrencyTakeEvent(
                                    Bukkit.getPlayer(args[1]), sender.getName(), Double.parseDouble(args[2]), false)
                            );
                        } else {
                            sender.sendMessage("Value must be number with or without decimals!");
                        }
                    } else {
                        sender.sendMessage("Player doesn't exist!");
                    }

                } else if (args.length == 4) {

                    if (SQLGetter.data.exists(args[1])) {
                        if (Parsers.isDouble(args[2])) {
                            if (Parsers.isBoolean(args[3])) {
                                Bukkit.getPluginManager().callEvent(new DarkBitsPlayerCurrencyTakeEvent(
                                        Bukkit.getPlayer(args[1]), sender.getName(), Double.parseDouble(args[2]), Boolean.valueOf(args[3]))
                                );
                            } else {
                                sender.sendMessage("Wrong argument! Silent can be only true/false.");
                            }
                        } else {
                            sender.sendMessage("Value must be number with or without decimals!");
                        }
                    } else {
                        sender.sendMessage("Player doesn't exist!");
                    }

                } else {
                    sender.sendMessage("Nope, something went really wrong!");
                }
            } else {
                sender.sendMessage(new ChatUtils().format("&cSomething went wrong, please check database if it is connected. Try /darkbits reload"));
            }
        } else {
            sender.sendMessage(new ChatUtils().format(DarkBits.config.get().getString("Messages.noperms")));
        }
    }
}
