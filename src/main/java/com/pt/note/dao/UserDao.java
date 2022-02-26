package com.pt.note.dao;

import com.pt.note.po.User;
import com.pt.note.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public User queryUserByName(String userName) {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from tb_user where uname = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUname(userName);
                user.setUpwd(resultSet.getString("uPwd"));
                user.setNick(resultSet.getString("nick"));
                user.setHead(resultSet.getString("head"));
                user.setMood(resultSet.getString("mood"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User queryUserByName2(String userName) {
        String sql = "select * from tb_user where uname = ?";
        List<Object> list = new ArrayList<>();
        list.add(userName);
        User user = (User) BaseDao.queryRow(sql, list, User.class);
        return user;
    }
}
