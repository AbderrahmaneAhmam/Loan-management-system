package managers;

import java.sql.*;

class DBManager {
    private static final String DB_IP = "127.0.0.1";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "LoansManager";
    private static final String DB_URL = "jdbc:mysql://"+DB_IP+":"+DB_PORT+"/"+DB_NAME;
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    private Connection connection;
    private Statement statement;

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public DBManager(){
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement();
            System.out.println("success!");
        } catch (SQLException ex) {
            System.out.println("error! "+ex.getMessage());
        }
    }
}
