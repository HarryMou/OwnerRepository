package com.usth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usth.entity.User;
import com.usth.service.UserService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class selectAllServlet extends javax.servlet.http.HttpServlet {

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
//        SqlSession sqlSession = MybatisUtil.getSqlSession();
//        UserDao dao = sqlSession.getMapper(UserDao.class);
//        List<User> userList = dao.selectAll();
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");

//        WebApplicationContext ctx = null;
//        Object obj = getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
//        if(null != obj){
//            ctx = (WebApplicationContext) obj;
//        }

        //使用框架中的方法来获取容器对象
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        UserService service = (UserService) ctx.getBean("userService");
        List<User> userList = service.findAll();
//        ObjectMapper om = new ObjectMapper();
        ObjectMapper om = (ObjectMapper) ctx.getBean("objectMapper");
        String json = om.writeValueAsString(userList);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(json);
//        sqlSession.close();
    }
}
