package xyz.geik.glib.file;

import de.leonhard.storage.Config;
import de.leonhard.storage.Json;
import xyz.geik.glib.GLibAPI;

public class FileRegisterUtil {
    public static Config config;
    public static Json hologramData;

    public FileRegisterUtil() {
        config = new Config("config.yml", "plugins/" + GLibAPI.getInstance().getDescription().getName(), GLibAPI.getInstance().getResource("config.yml"));
    }
}
