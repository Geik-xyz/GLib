package xyz.geik.glib.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import lombok.Getter;
import lombok.Setter;

/**
 * @author geik
 * @since 1.0
 */
@Getter
@Setter
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class SQLConfig extends OkaeriConfig {

    @Comment({"If you don't know about database settings", "please don't change here. Leave it SQLite"})
    private Database database = new Database();

    /**
     * Database configuration settings
     * @author geik
     * @since 2.0
     */
    @Getter @Setter
    public static class Database extends OkaeriConfig {
        private String databaseType = "SQLite";
        private String host = "localhost";
        private String port = "3306";
        private String tableName = "table";
        private String userName = "user";
        private String password = "supersecretpassword";
    }
}
