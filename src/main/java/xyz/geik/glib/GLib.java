package xyz.geik.glib;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class GLib {

    @Getter
    private static String prefix;

    @Getter
    private static JavaPlugin instance;

    public GLib(JavaPlugin plugin, String prefix) {
        GLib.instance = instance;
        GLib.prefix = prefix;
    }
}
