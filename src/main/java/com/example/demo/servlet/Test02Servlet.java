package com.example.demo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.servlet
 * @Date: 2017/12/1
 * @Time: 14:14
 */
@WebServlet(urlPatterns = "/test02")
public class Test02Servlet extends HttpServlet {

    /**
     * @Author: rogue
     * @Description: 重写doGet方法
     * @ClassName: Test02Servlet
     * @Date: 2017/12/1
     * @Time: 14:14
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置返回类型为json
        resp.setContentType("application/json");
        //设置返回字符集
        resp.setCharacterEncoding("utf-8");
        //输出对象
        PrintWriter writer = resp.getWriter();
        //输出error信息
        writer.write("执行test02servlet方法成功");
        writer.close();
    }
}
