package com.pt.note.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.pt.note.dao.UserDao;
import com.pt.note.po.User;
import com.pt.note.vo.ResultInfo;

public class UserService {
    UserDao userDao = new UserDao();

    public ResultInfo<User> userLogin(String userName, String userPwd) {
        ResultInfo<User> resultInfo = new ResultInfo<>();
        //数据回显：当登录实现时，将登录信息返回给页面显示
        User u = new User();
        u.setUname(userName);
        u.setUpwd(userPwd);
        resultInfo.setResult(u);
        if (StrUtil.isBlank(userName) || StrUtil.isBlank(userPwd)) {
            resultInfo.setCode(0);
            resultInfo.setMsg("用户名或密码不能为空");
            return resultInfo;
        }
        User user = userDao.queryUserByName(userName);
        if (user == null) {
            resultInfo.setCode(0);
            resultInfo.setMsg("用户不存在");
            return resultInfo;
        }
//            userPwd = DigestUtil.md5Hex(userPwd);
        if (!userPwd.equals(user.getUpwd())) {
            resultInfo.setCode(0);
            resultInfo.setMsg("密码错误");
            return resultInfo;
        }
        resultInfo.setCode(1);
        resultInfo.setResult(user);
        return resultInfo;
    }
}
