package xyz.geik.glib.database;


import lombok.SneakyThrows;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Database API for plugin.
 *
 * @author poyrazinan
 */
public class SQLite implements Database {

    /**
     * Connects database of plugin.
     *
     * @return Connection
     */
    @Override
    public Connection getConnection() {
        try {
            // JDBC Class for SQLite
            Class.forName("org.sqlite.JDBC");
            // Getting plugin name for folder destination
            String pluginName = DatabaseAPI.instance.getDescription().getName();
            // Getting file
            File databaseFile = new File("plugins/" + pluginName + "/storage/database.db");
            // Going in
            databaseFile.getParentFile().mkdirs();
            // Catching absolute path
            String absolutePath = databaseFile.getParentFile().getAbsolutePath();
            // Then using driver and return it.
            return DriverManager.getConnection("jdbc:sqlite:" + absolutePath + "/database.db");
        }
        catch (Exception e) { e.printStackTrace(); return null; }
    }


    @Override
    public void initSQL(String query) {
        File databaseFile = new File("plugins/" + DatabaseAPI.instance.getDescription().getName() + "/database.db");
        if (!databaseFile.exists()) {
            try {
                Class.forName("org.sqlite.JDBC");
                String url = "jdbc:sqlite:" + databaseFile.getParentFile().getAbsolutePath() + "/database.db";
                Connection connection = DriverManager.getConnection(url);
                connection.close();
            } catch(SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates table if not exists.
     * <p></p>
     * <b>Categories:</b>
     * <ul>
     *     <li>id (INTEGER PK AI), // <b>Primary key, auto increment</b></li>
     *     <li>data_name (varchar(32), No Null, unique), // <b>Data name of category</b></li>
     *     <li>category_status (int, not null, default 3), // <b>Time status of category</b></li>
     * </ul>
     * <p></p>
     * <b>Players:</b>
     * <ul>
     *     <li>uuid (varchar(32), not null), // <b>uuid of player</b></li>
     *     <li>category_id (int, not null), // <b>category id of delivery</b></li>
     *     <li>checkpoint (smallint, default 0), // <b>checkpoint situation of player</b></li>
     *     <li>deliver_amount (int, default 0), // <b>deposit amount of player to this category</b></li>
     *
     * </ul>
     */
    @SneakyThrows
    @Override
    public void createTables(String query) {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        // Adding batch of table creation to the statement
        statement.addBatch(query);
        // executing batch
        statement.executeBatch();
        connection.close();
    }
}
