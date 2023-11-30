package xyz.geik.glib.database;

import java.sql.Connection;

/**
 * Database interface
 */
interface Database {

    /**
     * Connection of SQL
     * @return Connection
     */
    Connection getConnection();

    /**
     * Initialize the sql
     */
    void initSQL(String query);

    void createTables(String query);
}