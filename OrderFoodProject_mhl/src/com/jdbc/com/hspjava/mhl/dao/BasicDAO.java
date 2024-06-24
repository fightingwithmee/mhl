package com.hspedu.com.hspjava.mhl.dao;

import com.hspedu.com.hspjava.mhl.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @autho 韩顺平
 * @versio 1.0
 */
public class BasicDAO<T> {

    private static QueryRunner qr = new QueryRunner();


    public int update(String sql,Object... para) {

        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            int update = qr.update(connection, sql,para);
            return update;
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    public List<T> manyQuery(String sql, Class<T> tClass, Object... para) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            List<T> query = qr.query(connection, sql, new BeanListHandler<T>(tClass), para);
            return query;
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }


    public T singleQuery(String sql, Class<T> tClass, Object... para) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            T query = qr.query(connection, sql, new BeanHandler<T>(tClass), para);
            return query;
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }
    public Object queryScalar(String sql, Class<T> tClass,Object... para) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            Object query = qr.query(connection, sql, new ScalarHandler(),para);
            return query;
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }



}
