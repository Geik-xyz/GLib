package xyz.geik.glib.utils;

import org.bukkit.ChatColor;

public class ColorAPI {

    public static String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
