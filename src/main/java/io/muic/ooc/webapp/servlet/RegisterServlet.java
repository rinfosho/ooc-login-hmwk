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

/**
 *
 * @author gigadot
 */
public class RegisterServlet extends HttpServlet {

    private SecurityService securityService;
    private DatabaseService db = new DatabaseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/signup.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String iusername = request.getParameter("username");
        String ipassword = request.getParameter("password");
        String ifirstname = request.getParameter("fname");
        if (!StringUtils.isBlank(iusername) && !StringUtils.isBlank(ipassword) && !StringUtils.isBlank(ifirstname)) {
            if (!db.readData().containsKey(iusername)) {
                db.insertDB(iusername,ipassword,ifirstname);
                response.sendRedirect("/login");
            } else {
                String error = "Please choose another username. ";
                request.setAttribute("error", error);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/signup.jsp");
                rd.include(request, response);
            }
        } else {
            String error = "Please fill in all the requirement form.";
            request.setAttribute("error", error);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/signup.jsp");
            rd.include(request, response);
        }
    }
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
    public void setMySQLService(DatabaseService db) {

        this.db  = db;
    }
}
