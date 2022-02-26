package com.pt.note.util;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static Properties properties = new Properties();
    static {
        try {
            InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
//            InputStream in1 = DBUtil.class.getResourceAsStream("db.properties");//测试报错
//            InputStream in2 = new BufferedInputStream(new FileInputStream("db.properties"));//测试报错
//            properties.load(in);
//            properties.load(new FileReader("D:\\coders\\IDEAPRO\\note\\src\\main\\resources\\db.properties"));
            properties.load(new FileReader("/coders/IDEAPRO/note/src/main/resources/db.properties"));
            Class.forName(properties.getProperty("jdbcName"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            String dbUrl = properties.getProperty("dbUrl");
            String dbName = properties.getProperty("dbName");
            String dbPwd = properties.getProperty("dbPwd");
            connection = DriverManager.getConnection(dbUrl, dbName, dbPwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
