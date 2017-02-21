/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.service.DatabaseService;
import io.muic.ooc.webapp.service.SecurityService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class UserAddServlet extends HttpServlet {

    SecurityService securityService;
//    DatabaseService databaseService;

//    public void setDatabaseService(DatabaseService databaseService) {
//        this.databaseService = databaseService;
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if (authorized) {
            // do MVC in here
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/adduser.jsp");
            rd.include(request, response);
        }else {
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");

        if (securityService.isAuthorized(request)) {

            if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {

                if (securityService.allowAdd(username)) {
                    PrintWriter out = response.getWriter();
                    new DatabaseService().insertDB(username,securityService.hashPassword(password),firstname);
//                    new DatabaseService().insertDB(username,password,firstname);
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
                    rd.include(request, response);
                    out.print("<p style=\"color:green\">Add User Successful</p>");
                } else {
                    String error = "Username already exist";
                    request.setAttribute("error", error);
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/adduser.jsp");
                    rd.include(request, response);
                }
            } else {
                String error = "Username or password is missing.";
                request.setAttribute("error", error);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/adduser.jsp");
                rd.include(request, response);
            }
        }else{
            response.sendRedirect("/");
        }
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
