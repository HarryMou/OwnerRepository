package com.usth.controller;

import com.usth.service.UserService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class deleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        SqlSession sqlSession = MybatisUtil.getSqlSession();
//        UserDao dao = sqlSession.getMapper(UserDao.class);
//        int num = dao.deleteOne(Integer.valueOf(request.getParameter("userid")));
//        if(num!=0){
//            sqlSession.commit();
//        }

        //创建监听后，就不用每次请求都创建ApplicationContext
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        WebApplicationContext ctx = null;
//        Object obj = getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
//        if(null != obj){
//           ctx = (WebApplicationContext) obj;
//        }

        //使用框架中的方法来获取容器对象
        ServletContext context = getServletContext();
        ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);

        UserService service = (UserService) ctx.getBean("userService");
        int num = service.deleteOne(Integer.valueOf(request.getParameter("userid")));
        String s = "成功删除" + num + "条数据";
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(s);
//        sqlSession.close();
    }
}
