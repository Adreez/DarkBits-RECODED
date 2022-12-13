package sk.adr3ez.darkbits.events;

import org.bukkit.event.Listener;
import org.reflections.Reflections;
import sk.adr3ez.darkbits.DarkBits;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

public class ListenerManager {

    private final DarkBits plugin;

    public ListenerManager(DarkBits plugin) {
        this.plugin = plugin;
        registerListeners();
    }

    public void registerListeners() throws RuntimeException {
        String packageName = getClass().getPackage().getName();


        for (Class<?> clazz : new Reflections(packageName + ".listeners").getSubTypesOf(Listener.class)) {
            try {
                Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
                plugin.getServer().getPluginManager().registerEvents(listener, plugin);
                plugin.getLogger().log(Level.INFO, "Listener " + clazz.getName() + " has been loaded!");
            } catch (RuntimeException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                     InstantiationException e) {
                e.printStackTrace();
            }
        }

    }

}
