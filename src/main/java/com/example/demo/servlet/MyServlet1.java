package com.example.demo.servlet;

import javax.servlet.ServletException;
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
 * @Time: 16:01
 */
public class MyServlet1 extends HttpServlet {

        private static final long serialVersionUID = 8031133938454140403L;

        @Override
        protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Hello World</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>welcome this is my servlet1!!!</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
