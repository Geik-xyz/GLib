package xyz.geik.glib.utils;

import org.bukkit.Bukkit;

public class Logger {
    public static void sendConsoleMessage(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(message);
    }
}
