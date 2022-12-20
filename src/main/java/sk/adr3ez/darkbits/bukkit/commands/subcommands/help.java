package sk.adr3ez.darkbits.bukkit.commands.subcommands;

import org.bukkit.command.CommandSender;
import sk.adr3ez.darkbits.bukkit.commands.CommandManager;
import sk.adr3ez.darkbits.bukkit.commands.SubCommand;
import sk.adr3ez.darkbits.bukkit.utils.ChatUtils;

public class help extends SubCommand {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Commands list";
    }

    @Override
    public String getUsage() {
        return "/darkbits help";
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (sender.hasPermission("darkbits.help")) {

            sender.sendMessage(new ChatUtils().format("&6&lDarkBits &7Commands list"));
            for (SubCommand subCommand : CommandManager.getSubCommands()) {
                sender.sendMessage(new ChatUtils().format("&e" + subCommand.getUsage() + " &7 " + subCommand.getDescription()));
            }
            sender.sendMessage(new ChatUtils().format("&7<required> [optional]"));

        }
    }
}
