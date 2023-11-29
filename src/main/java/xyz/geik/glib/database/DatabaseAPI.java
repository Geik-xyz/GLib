package xyz.geik.glib.database;

import lombok.Getter;
import xyz.geik.glib.configuration.SQLConfig;

/**
 * @author geik
 * @since 1.0
 */
@Getter
public class DatabaseAPI {

    @Getter
    public static DatabaseAPI databaseAPI;
    private SQLConfig databaseFile;

    public DatabaseAPI() {
        DatabaseAPI.databaseAPI = this;
        databaseFile = new SQLConfig();
    }
}
