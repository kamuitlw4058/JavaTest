package com.kimi.java.lib.derby;

import java.nio.file.*;
        import java.sql.*;
        import java.io.*;
        import java.util.*;

/*
 *This program tests that the database and the JDBC driver are correctly configured.
 */

public class UseDerby{
    public static void main(String[] args) throws IOException{
        try{
            runTest();

//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
//            System.out.println("Load the embedded driver");
//            Connection conn = null;
//            Properties props = new Properties();
//            props.put("user", "test");
//            props.put("password", "test");
//// create and connect the database named helloDB
//            conn = DriverManager.getConnection(
//                    "jdbc:derby:testDB;create=true", props);
//            System.out.println("create and connect to testDB");
//            conn.setAutoCommit(true);
        }catch(SQLException ex){
            for(Throwable t:ex)
                t.printStackTrace();
        }

    }
    /**
     *Runs a test by creating a table,adding a value,showing the table contents,and removing the table.
     */
    public static void runTest() throws SQLException,IOException{
        try(Connection conn = getConnection())
        {
            Statement stat = conn.createStatement();
            stat.executeUpdate("CREATE TABLE Greetings (Message CHAR(40))");

            // Using ' not "
            stat.executeUpdate("INSERT INTO Greetings VALUES('hello')");
            stat.executeUpdate("INSERT INTO Greetings VALUES('你好，世界')");

            try(ResultSet result = stat.executeQuery("SELECT * FROM Greetings")){
                //将光标移动到下一行，初始在第一行之前
                while(result.next())
                    System.out.println(result.getString("Message"));
            }
            stat.executeUpdate("DROP TABLE Greetings");

        }
    }
    /**
     *Gets a connection from the properties specified in the file database.properties.
     *@return the database connection
     */
    public static Connection getConnection() throws SQLException,IOException{
//        Properties props = new Properties();
//        try(InputStream in = Files.newInputStream(Paths.get("database.properties"))){
//            props.load(in);
//        }
//        String drivers = props.getProperty("jdbc.drivers");
//        //为了适应那些不能自动注册的数据库驱动程序
//        if(drivers != null)
//            //这种方式可以提供多个驱动器，使用冒号分割
//            System.setProperty("jdbc.drivers","org.apache.derby.jdbc.EmbeddedDriver");
        //String url = props.getProperty("jdbc.url");
        //String username = props.getProperty("jdbc.username");
        //String password = props.getProperty("jdbc.password");
        return DriverManager.getConnection( "jdbc:derby:testDB;create=true","test","test");
    }




}
