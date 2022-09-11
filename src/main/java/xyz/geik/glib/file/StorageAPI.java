package xyz.geik.glib.file;

import de.leonhard.storage.Config;
import de.leonhard.storage.Json;
import org.bukkit.plugin.Plugin;
import xyz.geik.glib.GLibAPI;
import xyz.geik.glib.utils.ColorAPI;

public class StorageAPI {
    private static Plugin plugin = GLibAPI.getInstance();
    private static String prefix = null;

    public StorageAPI() {
        
    }

    public Config initConfig(String fileName) {
        if (StorageAPI.plugin == null)
            return null;

        fileName += ".yml";
        return new Config(fileName, "plugins/" + StorageAPI.plugin.getDescription().getName(), StorageAPI.plugin.getResource(fileName));
    }

    public Json initJson(String fileName) {
        if (StorageAPI.plugin == null)
            return null;

        fileName += ".json";
        return new Json(fileName, "plugins/" + StorageAPI.plugin.getDescription().getName(), StorageAPI.plugin.getResource(fileName));
    }

    public void setPrefix(String prefix) {
        StorageAPI.prefix = prefix;
    }

    public String replacePrefix(String text) {
        if (StorageAPI.prefix == null)
            return text;

        prefix = ColorAPI.colorize(prefix);
        return text.replaceAll("%prefix%", prefix);
    }

    public String replacePlayer(String text, String player) {
        return text.replaceAll("%player%", player);
    }

    public String getText(Config config, String key) {
        return replacePrefix(config.getText(key));
    }

    public String getText(Json json, String key) {
        return replacePrefix(json.getText(key));
    }
}
