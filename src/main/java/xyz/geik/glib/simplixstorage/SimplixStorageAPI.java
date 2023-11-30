package xyz.geik.glib.simplixstorage;

import de.leonhard.storage.Config;
import de.leonhard.storage.Json;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class SimplixStorageAPI {

    protected JavaPlugin instance;

    public SimplixStorageAPI(JavaPlugin plugin) {
        instance = plugin;
    }

    /**
     * Initiating config file
     *
     * @param fileName name of the file
     * @return Config
     * @see Config
     */
    public Config initConfig(String fileName) {
        return initConfig(fileName, instance);
    }

    /**
     * Initiating lang file into lang folder
     *
     * @param fileName name of the file
     * @return Config
     * @see Config
     */
    public Config initLangFile(String fileName) {
        Config config = initConfig("lang/" + fileName, instance);
        if (config == null)
            config = initConfig("lang/en", instance);
        return config;
    }

    /**
     * Initiating config file for addons
     *
     * @param fileName name of the file
     * @param plugin  plugin instance
     * @return Config
     * @see Config
     */
    public Config initConfig(String fileName, @NotNull JavaPlugin plugin) {
        fileName += ".yml";
        return new Config(fileName, "plugins/" + plugin.getDescription().getName(),
                instance.getResource(fileName));
    }

    /**
     * Initiating json file
     *
     * @param fileName name of the file
     * @return Json
     * @see Json
     */
    public Json initJson(String fileName) {
        return initJson(fileName, instance);
    }

    /**
     * Initiating json file
     *
     * @param fileName name of the file
     * @param plugin main class of project
     * @return Json
     * @see Json
     */
    public Json initJson(String fileName, @NotNull JavaPlugin plugin) {
        fileName += ".json";
        return new Json(fileName, "plugins/" + plugin.getDescription().getName(),
                instance.getResource(fileName));
    }
}
