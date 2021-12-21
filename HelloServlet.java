package com.example.demo;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

class User{
    String login;
    String pass;

    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }
}

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private User user;

    public void init() {
        message = "Hello World!";
    }

    private int bananas = 0;
    private int apples = 0;
    private int carrots = 0;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setAttribute("tres", 999);

        if (request.getParameter("enter") != null) {
            user = new User(request.getParameter("login"),
                    request.getParameter("pass"));
            request.getRequestDispatcher("products.jsp").forward(request, response);
        }
        if (request.getParameter("apples") != null) {
            apples++;
            request.getRequestDispatcher("products.jsp").forward(request, response);
        }
        if (request.getParameter("bananas") != null) {
            bananas++;
            request.getRequestDispatcher("products.jsp").forward(request, response);
        }
        if (request.getParameter("carrots") != null) {
            carrots++;
            request.getRequestDispatcher("products.jsp").forward(request, response);
        }
        if (request.getParameter("cart") != null){
            request.setAttribute("apples", apples);
            request.setAttribute("carrots", carrots);
            request.setAttribute("bananas", bananas);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
        if (request.getParameter("back") != null){
            response.sendRedirect("products.jsp");
        }
        if (request.getParameter("pay") != null){
            request.setAttribute("apples", apples);
            request.setAttribute("carrots", carrots);
            request.setAttribute("bananas", bananas);
            request.getRequestDispatcher("pay.jsp").forward(request, response);
        }
        if (request.getParameter("pp") != null){
            response.getWriter().println("<html>\n" +
                    "<head>\n" +
                    "    <title>Success!</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>Payment successful!</h1>\n" +
                    "</body>\n" +
                    "</html>");
        }
        if (request.getParameter("trs") != null){
            //2-3-4
            request.setAttribute("tres",Integer.parseInt(request.getParameter("tresValue")));
            request.getRequestDispatcher("products.jsp").forward(request, response);
        }
    }

    public void destroy() {
    }
}