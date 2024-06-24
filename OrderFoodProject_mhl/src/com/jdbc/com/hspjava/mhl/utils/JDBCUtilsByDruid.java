package com.hspedu.com.hspjava.mhl.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.support.spring.stat.annotation.Stat;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @autho 韩顺平
 * @versio 1.0
 */
public class JDBCUtilsByDruid {

    private static DataSource ds;

    static {
        try {
            Properties properties = new Properties();
//            properties.load(new FileInputStream("E:\\hspjava\\mhl\\src\\druid.properties"));
            properties.load(new FileInputStream("E:\\hspjava\\mhl\\OrderFoodProject_mhl\\src\\druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void close(ResultSet set, Statement statement,Connection connection){
        try {
            if(set != null){
                set.close();
            }
            if(statement != null){
                statement.close();
            }
            if(connection != null){

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
