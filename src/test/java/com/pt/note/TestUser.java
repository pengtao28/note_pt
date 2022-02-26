package com.pt.note;

import com.pt.note.dao.BaseDao;
import com.pt.note.dao.UserDao;
import com.pt.note.po.User;
import javafx.beans.binding.ObjectExpression;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class TestUser {
    @Test
    public void testQueryUserByName() {
        UserDao userDao = new UserDao();
        User user = userDao.queryUserByName("pt");
        System.out.println(user.getUpwd());
    }

    @Test
    public void testQueryUserByName2() {
        UserDao userDao = new UserDao();
        User user = userDao.queryUserByName2("pt");
        System.out.println(user.getUpwd());
    }

    @Test
    public void testAdd() {
        String sql = "insert into tb_user(uname,upwd,nick,head,mood) values(?,?,?,?,?)";
        List<Object> list = new ArrayList<>();
        list.add("admin");
        list.add("123");
        list.add("admin");
        list.add("404.jpg");
        list.add("Hello");
        int row = BaseDao.executeUpdate(sql, list);
        System.out.println(row);
    }
}
