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

/**
 * Created by JohnnyV on 2/16/2017 AD.
 */
public class UserEditServlet extends HttpServlet {

    private SecurityService securityService;
    private String toEditId;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // cant access if not authenticated
        if (securityService.isAuthorized(request)) {
            String id = request.getParameter("id");

            toEditId = id;

            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/edituser.jsp");
            rd.include(request, response);
        }else{
            response.sendRedirect("/");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String username = request.getParameter("username");
        String firstname = request.getParameter("firstname");

        if (securityService.isAuthorized(request)) {

            if (!StringUtils.isBlank(username) && !StringUtils.isBlank(firstname)) {

                PrintWriter out = response.getWriter();
                new DatabaseService().updateDB(toEditId, username, firstname);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
                rd.include(request, response);
                out.print("<p style=\"color:green\">Edit User Successful</p>");
            } else {
                String error = "Username, Password, or Firstname is missing.";
                request.setAttribute("error", error);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/edituser.jsp");
                rd.include(request, response);
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
            rd.include(request, response);
        }
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
