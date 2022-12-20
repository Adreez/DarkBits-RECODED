package sk.adr3ez.darkbits.bukkit.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sk.adr3ez.darkbits.bukkit.DarkBits;
import sk.adr3ez.darkbits.bukkit.commands.SubCommand;
import sk.adr3ez.darkbits.bukkit.events.custom.DarkBitsPlayerCurrencySendEvent;
import sk.adr3ez.darkbits.bukkit.sql.SQLGetter;
import sk.adr3ez.darkbits.bukkit.utils.ChatUtils;
import sk.adr3ez.darkbits.bukkit.utils.Parsers;

public class send extends SubCommand {
    @Override
    public String getName() {
        return "send";
    }

    @Override
    public String getDescription() {
        return "Send amount of bits to another player";
    }

    @Override
    public String getUsage() {
        return "/darkbits send <player> <value>";
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("darkbits.send")) {

                if (DarkBits.mySQL.isConnected()) {
                    if (args.length == 3) {
                        if (SQLGetter.data.exists(args[1])) {
                            if (Parsers.isDouble(args[2])) {
                                Bukkit.getPluginManager().callEvent(new DarkBitsPlayerCurrencySendEvent(
                                        Bukkit.getPlayer(sender.getName()), Bukkit.getPlayer(args[1]), Double.parseDouble(args[2])));
                            } else {
                                sender.sendMessage("Value must be number with or without decimals!");
                            }
                        } else {
                            sender.sendMessage("Player doesn't exist! Make sure you have typed it correctly!");
                        }
                    } else {
                        sender.sendMessage(getUsage());
                    }
                } else {
                    sender.sendMessage(new ChatUtils().format("&cSomething went wrong, please contact server administrator."));
                }

            } else {
                sender.sendMessage(new ChatUtils().format(DarkBits.config.get().getString("Messages.noperms")));
            }
        } else {
            sender.sendMessage("&cConsole is not allowed to use this command!");
        }
    }
}
