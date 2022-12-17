package sk.adr3ez.darkbits.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;
import sk.adr3ez.darkbits.bukkit.utils.ChatUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private final ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager() {
        registerSubCommands();
    }

    public void registerSubCommands() throws RuntimeException {
        String packageName = getClass().getPackage().getName();


        for (Class<?> clazz : new Reflections(packageName + ".subcommands").getSubTypesOf(SubCommand.class)) {
            try {
                SubCommand subCmd = (SubCommand) clazz.getDeclaredConstructor().newInstance();
                subCommands.add(subCmd);
            } catch (RuntimeException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                     InstantiationException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if (args.length > 0 && !args[0].equalsIgnoreCase("help")) {
            for (SubCommand subCommand : subCommands) {
                if (args[0].equalsIgnoreCase(subCommand.getName())) {
                    subCommand.onCommand(sender, args);
                }
            }
        } else if (args[0].equalsIgnoreCase("help")) {
            if (sender.hasPermission("darkbits.help")) {

                sender.sendMessage(new ChatUtils().format("&6&lDarkBits &7Commands list"));
                for (SubCommand subCommand : subCommands) {
                    sender.sendMessage(new ChatUtils().format("&e" + subCommand.getUsage() + " &7 " + subCommand.getDescription()));
                }
                sender.sendMessage(new ChatUtils().format("&7<required> [optional]"));

            }
        } else if (args.length == 0) {
            //Send player account value
            sender.sendMessage("WIP");
        }
        return false;
    }
}
