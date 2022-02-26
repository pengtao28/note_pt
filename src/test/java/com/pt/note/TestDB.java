package com.pt.note;

import com.pt.note.dao.BaseDao;
import com.pt.note.po.User;
import com.pt.note.util.DBUtil;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestDB {
    //使用日志工厂类，记录日志
    private Logger logger = LoggerFactory.getLogger(TestDB.class);

    @Test
    public void testDB() {
        System.out.println(DBUtil.getConnection());
        logger.info("数据库连接" + DBUtil.getConnection());
        logger.info("数据库连接{}", DBUtil.getConnection());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入100");
        int a = scanner.nextInt();
//        System.out.println(a);
//        System.out.println("请输入张三：");
        String Ca = scanner.nextLine();
        System.out.println(Ca);
        System.out.println("请输入李四：");
        String b = scanner.nextLine();
        System.out.println(b);
    }
    @Test
    public void queryUserByName2() {
        String sql = "select nick from tb_user where uname = ?";
        List<Object> list = new ArrayList<>();
        list.add("admin");
        User user = (User) BaseDao.queryRow(sql, list, User.class);
        System.out.println(user.getNick());
    }
}
