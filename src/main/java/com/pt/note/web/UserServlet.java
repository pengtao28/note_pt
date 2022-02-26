package com.pt.note.web;

import com.pt.note.po.User;
import com.pt.note.service.UserService;
import com.pt.note.vo.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionName = request.getParameter("actionName");
        if ("login".equals(actionName)) {
            userLogin(request, response);
        }
    }

    private void userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String userPwd = request.getParameter("userPwd");
        ResultInfo<User> resultInfo = userService.userLogin(userName, userPwd);
        if (resultInfo.getCode() == 1) {
            //将用户信息设置到session作用域中
            request.getSession().setAttribute("user", resultInfo.getResult());
            String rem = request.getParameter("rem");
            if ("1".equals(rem)) {
                Cookie cookie = new Cookie("user", userName + "-" + userPwd);
                cookie.setMaxAge(3 * 24 * 60 * 60);
                response.addCookie(cookie);
            } else {
                Cookie cookie = new Cookie("user", null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            response.sendRedirect("index.jsp");
        } else {
            //将resultInfo对象设置到request作用域中
            request.setAttribute("resultInfo", resultInfo);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
