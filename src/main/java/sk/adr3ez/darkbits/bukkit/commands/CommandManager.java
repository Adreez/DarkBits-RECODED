package sk.adr3ez.darkbits.bukkit.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;
import sk.adr3ez.darkbits.bukkit.DarkBits;
import sk.adr3ez.darkbits.bukkit.commands.subcommands.TabCompleteManager;
import sk.adr3ez.darkbits.bukkit.sql.SQLGetter;
import sk.adr3ez.darkbits.bukkit.utils.ChatUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    public static ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }

    private static final ArrayList<SubCommand> subCommands = new ArrayList<>();
    public CommandManager(JavaPlugin plugin) {
        registerSubCommands();
        plugin.getCommand("darkbits").setTabCompleter(new TabCompleteManager());
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


        if (args.length > 0) {
            for (SubCommand subCommand : subCommands) {
                if (args[0].equalsIgnoreCase(subCommand.getName())) {
                    subCommand.onCommand(sender, args);
                }
            }
        }  else if (args.length == 0) {
            //Send player account value
            if (sender instanceof Player) {
                sender.sendMessage(new ChatUtils().format(String.valueOf(SQLGetter.data.getWallet(sender.getName()))));
            } else {
                sender.sendMessage(new ChatUtils().format("&7Use /darkbits help to show all commands."));
            }
        }
        return false;
    }
}
