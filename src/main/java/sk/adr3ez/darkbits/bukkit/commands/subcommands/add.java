package sk.adr3ez.darkbits.bukkit.commands.subcommands;

import org.bukkit.command.CommandSender;
import sk.adr3ez.darkbits.bukkit.DarkBits;
import sk.adr3ez.darkbits.bukkit.sql.SQLGetter;
import sk.adr3ez.darkbits.bukkit.commands.SubCommand;
import sk.adr3ez.darkbits.bukkit.utils.ChatUtils;

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
        return "/darkbits add <player> <value>";
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (sender.hasPermission(DarkBits.config.get().getString("Permissions.add"))) {
            if (args.length == 2) {
                if (SQLGetter.data.exists(args[2])) {
                    //Add to wallet if exists
                }
            }
        } else {
            sender.sendMessage(new ChatUtils().format(DarkBits.config.get().getString("Messages.noperms")));
        }
    }
}
