package com.usth.controller;

import com.usth.entity.User;
import com.usth.service.UserService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class insertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        SqlSession sqlSession = MybatisUtil.getSqlSession();
//        UserDao dao = sqlSession.getMapper(UserDao.class);
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");

//        WebApplicationContext ctx = null;
//        Object obj = getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
//        if(null != obj){
//            ctx = (WebApplicationContext) obj;
//        }

        //使用框架中的方法来获取容器对象
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        UserService service = (UserService) ctx.getBean("userService");
//        User user = new User();
        User user = (User) ctx.getBean("user");
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setSex(request.getParameter("sex"));
        user.setEmail(request.getParameter("email"));
        int num = service.addOne(user);
//        int num = dao.insertOne(user);
//        if(num!=0){
//            sqlSession.commit();
//        }
        String s = "成功插入" + num + "条数据";
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(s);
//        sqlSession.close();
    }
}
