package com.usth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.List;

public class selectOneServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        String key = request.getParameter("k");
        String value = request.getParameter("value");
//        List<User> userList = dao.selectOne(key,value);
        List<User> userList = service.findOne(key,value);
//        ObjectMapper om = new ObjectMapper();
        ObjectMapper om = (ObjectMapper) ctx.getBean("objectMapper");
        String json = om.writeValueAsString(userList);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(json);
//        sqlSession.close();
    }
}
