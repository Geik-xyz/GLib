package xyz.geik.glib;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import space.arim.morepaperlib.MorePaperLib;

public class GLib {

    @Getter
    private static String prefix;

    @Getter
    private static JavaPlugin instance;
    @Getter
    private static MorePaperLib morePaperLib;

    public GLib(JavaPlugin plugin, String prefix) {
        GLib.instance = plugin;
        GLib.prefix = prefix;
        GLib.morePaperLib = new MorePaperLib(plugin);
    }
}
