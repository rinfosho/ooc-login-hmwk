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
public class UserDeleteServlet extends HttpServlet {

    SecurityService securityService;
    private String toDeleteId;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (securityService.isAuthorized(request)) {
            String id = request.getParameter("id");

            toDeleteId = id;

//            System.out.println(toEditId);

            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/deluser.jsp");
            rd.include(request, response);
        }else{
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String uid = (String) request.getSession()
//                .getAttribute("password");

//        System.out.println(uid);

        if (securityService.isAuthorized(request)) {
            PrintWriter out = response.getWriter();
            new DatabaseService().deleteDB(toDeleteId);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
            rd.include(request, response);
            out.print("<p style=\"color:green\">Delete User Successful</p>");
        }else {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
            rd.include(request, response);
        }
//
//            if () {
//
//                PrintWriter out = response.getWriter();
//                new DatabaseService().deleteDB(toDeleteId);
//                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
//                rd.include(request, response);
//                out.print("<p style=\"color:green\">Delete User Successful</p>");
//            } else {
//                String error = "Username, Password, or Firstname is missing.";
//                request.setAttribute("error", error);
//                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/edituser.jsp");
//                rd.include(request, response);
//            }
//        } else {
//            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
//            rd.include(request, response);
//        }
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
