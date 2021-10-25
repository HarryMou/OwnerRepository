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

public class updateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        SqlSession sqlSession = MybatisUtil.getSqlSession();
//        UserDao dao = sqlSession.getMapper(UserDao.class);

        //创建监听后，就不用每次请求都创建ApplicationContext
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
        user.setUserid(Integer.valueOf(request.getParameter("userid")));
        user.setPassword(request.getParameter("password"));
        user.setSex(request.getParameter("sex"));
        user.setEmail(request.getParameter("email"));
        int num = service.updateOne(user);
//        int num = dao.updateOne(user);
//        if(num!=0){
//            sqlSession.commit();
//        }
        String s = "成功更新" + num + "条数据";
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(s);
//        sqlSession.close();
    }

}
