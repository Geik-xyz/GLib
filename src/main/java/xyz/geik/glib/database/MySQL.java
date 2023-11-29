package xyz.geik.glib.database;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MySQL implements Database {

    private String host, port, dbName, userName, password;

    public MySQL() {
        this.host = DatabaseAPI.getDatabaseAPI().getDatabaseFile().getDatabase().getHost();
        this.port = DatabaseAPI.getDatabaseAPI().getDatabaseFile().getDatabase().getPort();
        this.dbName = DatabaseAPI.getDatabaseAPI().getDatabaseFile().getDatabase().getTableName();
        this.userName = DatabaseAPI.getDatabaseAPI().getDatabaseFile().getDatabase().getUserName();
        this.password = DatabaseAPI.getDatabaseAPI().getDatabaseFile().getDatabase().getPassword();
    }

    @Override
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
            return DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void initSQL(String query) {
        createTables(query);
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
