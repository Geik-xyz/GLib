package xyz.geik.glib.database;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author geik
 * @since 1.0
 */
@Getter
public class DatabaseAPI {

    @Getter
    private Database database;

    protected static JavaPlugin instance;

    public DatabaseAPI(JavaPlugin instance) {
        DatabaseAPI.instance = instance;
        database = new SQLite();
    }

    public DatabaseAPI(JavaPlugin instance, String host, String port, String dbName, String userName, String password) {
        DatabaseAPI.instance = instance;
        database = new MySQL(host, port, dbName, userName, password);
    }
}
