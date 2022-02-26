package com.pt.note.dao;

import com.pt.note.util.DBUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

public class BaseDao {
    //更新操作（包括：增、删、改）
    public static int executeUpdate(String sql, List<Object> params) {
        int row = 0;//受影响的行数
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (params != null && params.size() > 0) {
                for (int i = 0; i < params.size(); i++) {
                    preparedStatement.setObject(i + 1, params.get(i));
                }
            }
            row = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
        return row;
    }

    //查询操作
    //查询一个字段
    public static Object querySingleValue(String sql, List<Object> params) {
        Object object = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (params != null && params.size() > 0) {
                for (int i = 0; i < params.size(); i++) {
                    preparedStatement.setObject(i + 1, params.get(i));
                }
            }
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                object = resultSet.getObject(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
        return object;
    }

    //查询集合
    public static List queryRows(String sql, List<Object> params, Class cls) {
        List<Object> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (params != null && params.size() > 0) {
                for (int i = 0; i < params.size(); i++) {
                    preparedStatement.setObject(i + 1, params.get(i));
                }
            }
            resultSet = preparedStatement.executeQuery();
            //得到结果集的元数据对象
            ResultSetMetaData metaData = resultSet.getMetaData();
            //得到查询的字段数量
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                //实例化对象
                Object object = cls.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    //得到查询的每一个列名
                    String columnLabel = (metaData.getColumnLabel(i)).toLowerCase();
                    Field file = cls.getDeclaredField(columnLabel);
//                    //拼接set方法得到字符串
//                    String setMethod = "set" + columnLabel.substring(0, 1).toUpperCase() + columnLabel.substring(1);
//                    //通过反射将set方法字符串反射成类中对应的set方法
//                    Method method = cls.getDeclaredMethod(setMethod, file.getType());
//                    //得到查询字段所对应的值
                    Object value = resultSet.getObject(columnLabel);
//                    //通过invoke方法调用set方法
//                    method.invoke(object, value);
                    file.setAccessible(true);
                    file.set(object, value);
                }
                //将Javabean设置到集合中
                list.add(object);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
        return list;
    }

    //查询对象
    public static Object queryRow(String sql, List<Object> params, Class cls) {
        List list = queryRows(sql, params, cls);
        Object object = null;
        if (list != null && list.size() > 0) {
            object = list.get(0);
        }
        return object;
    }
}
