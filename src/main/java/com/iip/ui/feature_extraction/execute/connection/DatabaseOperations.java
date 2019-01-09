package com.iip.ui.feature_extraction.execute.connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {
    // JDBC驱动器名称
    public static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    // 数据库的名称
    public static String DB_URL_prefix = "jdbc:mysql://127.0.0.1:3306/";
    public static String DBNAME = "kjb_fe";
    public static String DB_URL = "jdbc:mysql://127.0.0.1:3306/kjb_fe";
    // 数据库用户和密码
    public static String USER = "root";
    public static String PASS = "123456";

    // 数据库连接相关类
    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet resultSet = null;

    //数据库相关参数
    public static String originalDataTables[] = {"data_original", "data_original", "data_original"};
    public static String outputTableNames[] = {"output_words", "output_key_words", "output_vector", "test"};
    public static final String dataColumn = "data";
    public static String sql = "SHOW TABLES LIKE ";  //  + originalDataTables[0]

    public static boolean isReady = false;
    public static String k = "5";
    public static String path = "../zhwiki_50d.word2vec";

    public static boolean connectDB(){
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            print("Connect to database success!");
            for(String originTable: originalDataTables){
                resultSet = statement.executeQuery(sql+"'"+originTable+"';");
                if(!resultSet.next()) return false;
            }
            for(String tablename: outputTableNames){
                String createTableSql = "CREATE TABLE IF NOT EXISTS "+tablename+"(" +
                        "ID INT NOT NULL AUTO_INCREMENT, "+
                        "data TEXT, "+
                        "PRIMARY KEY (ID)) CHARSET=utf8;";
//                resultSet = statement.executeLargeUpdate(createTableSql);//executeQuery(createTableSql);
                if(statement.executeLargeUpdate(createTableSql)==0){
                    print("create table success!");
                }else{
                    print("fail to create table");
                }
            }
            return true;
        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static List<String> read(String table, String column){
        List<String> data = new ArrayList<>();
        try {
            String readTableSql = "SELECT "+column+" from "+table;
            resultSet = statement.executeQuery(readTableSql);
            while (resultSet.next()){
                data.add(resultSet.getString(column));
            }
        }catch (SQLException se){ se.printStackTrace(); }
        return data;
    }

    public static void write(String table, String column, List<String> data){
        int cnt = 0;
        String queries[] = new String[data.size()];
        for(String d: data){
            queries[cnt] = "INSERT into "+table+" ("+column+") values ('"+d+"');";
            cnt += 1;
        }
        try {
            for(String query:queries){
                statement.addBatch(query);
            }
            statement.executeBatch(); // when is success
            print("insert success!");
        }catch (SQLException se){ se.printStackTrace(); }
    }

    public static void disconnect(){
        try {
            if (statement != null) statement.close();
        } catch (SQLException se2) { se2.printStackTrace(); }
        try {
            if (connection != null) connection.close();
        } catch (SQLException se) { se.printStackTrace(); }
        print("数据库断开连接!");
    }

    public static void main(String a[]){
        DatabaseOperations.connectDB(); //connect 操作
        List<String> r = read(originalDataTables[0], dataColumn); //read 操作
        write(outputTableNames[3], dataColumn, r); //write 操作
        disconnect(); //disconnect 操作
//        try {
//            //resultSet = statement.executeQuery(sql);
//        }catch (SQLException se){ se.printStackTrace(); }
//            resultSet = statement.executeQuery(sql);
//            while (resultSet.next()){
//                int id = resultSet.getInt("ID");
//                String data = resultSet.getString("data");
//                print(id + ": " + data);
//            }
    }

    public static void print(String s){
        System.out.println(s);
    }
}
