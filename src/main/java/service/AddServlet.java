package service;


import Dao.UserDao;
import User.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset = UTF-8");
        PrintWriter out =response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        System.out.println("23232323");
        UserDao u =new UserDao();
        HttpSession session =request.getSession();
        //获取时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String regeTime = sdf.format(new Date());
        session.setAttribute("user",user);
        session.setAttribute("regeTime",regeTime);
        session.setAttribute("message","你注册成功了");

        try {
            if(u.addUser(user)){
                System.out.println("注册成功");
                request.getRequestDispatcher("Upload.jsp").forward(request,response);
            }
            else{
                System.out.println("注册失败");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //从定向

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
