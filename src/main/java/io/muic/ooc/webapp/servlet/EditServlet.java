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
public class EditServlet extends HttpServlet {
    DatabaseService db = new DatabaseService();

    private SecurityService securityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/edit.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // do login post logic
        // extract username and password from request
        String username = request.getParameter("username");
        String firstname = request.getParameter("firstname");
        if (!StringUtils.isBlank(username)&&!StringUtils.isBlank(firstname)){
            db.updateDB(username,firstname);
        }
        else{
            String error = "Please do not leave any blanks. If you want username/firstname to remain the same, please " +
                    "enter the old username/firstname";
            request.setAttribute("error", error);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/edit.jsp");
            rd.include(request, response);
        }



        // check username and password against database
        // if valid then set username attribute to session via securityService
        // else put error message to render error on the login form

    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
