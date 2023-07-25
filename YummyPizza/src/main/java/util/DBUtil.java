package util;

import java.sql.*;

/**
 * @author Xiang Weng
 */
public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/yummy_pizza";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    static{
        try{
            // load the class into JVM
            Class.forName(DRIVER);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //get the connection to the DB
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(Connection connection){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void closePs(PreparedStatement preparedStatement){
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void closeRs(ResultSet resultSet){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
