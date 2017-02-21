/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp;

import io.muic.ooc.webapp.service.DatabaseService;
import io.muic.ooc.webapp.servlet.*;
import io.muic.ooc.webapp.service.SecurityService;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;


public class ServletRouter {
    
    private SecurityService securityService;
//    private DatabaseService databaseService;

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

//    public void setDatabaseService(DatabaseService databaseService) {
//        this.databaseService = databaseService;
//    }

    public void init(Context ctx) {
        initHome(ctx);
        initLogin(ctx);
        initLogout(ctx);
        initAddUser(ctx);
        initEditUser(ctx);
        initDeleteUser(ctx);
    }

    private void initHome(Context ctx) {
        HomeServlet homeServlet = new HomeServlet();
        homeServlet.setSecurityManager(securityService);
        Tomcat.addServlet(ctx, "HomeServlet", homeServlet);
        ctx.addServletMapping("/index.jsp", "HomeServlet");
    }

    private void initLogin(Context ctx) {
        LoginServlet loginServlet = new LoginServlet();
        loginServlet.setSecurityService(securityService);
        Tomcat.addServlet(ctx, "LoginServlet", loginServlet);
        ctx.addServletMapping("/login", "LoginServlet");
    }

    private void initLogout(Context ctx) {
//        System.out.println("logout");
        LogoutServlet logoutServlet = new LogoutServlet();
        logoutServlet.setSecurityService(securityService);
        Tomcat.addServlet(ctx, "LogoutServlet", logoutServlet);
        ctx.addServletMapping("/logout", "LogoutServlet");
    }

    private void initAddUser(Context ctx) {
//        System.out.println("user");
        UserAddServlet userAddServlet = new UserAddServlet();
        userAddServlet.setSecurityService(securityService);
//        userAddServlet.setDatabaseService(databaseService);
        Tomcat.addServlet(ctx, "UserAddServlet", userAddServlet);
        ctx.addServletMapping("/adduser", "UserAddServlet");
//        ctx.addServletMapping("/edituser", "UserEditServlet");
//        ctx.addServletMapping("/deluser", "UserDeleteServlet");
    }

    private void initEditUser(Context ctx) {
//        System.out.println("edit");
        UserEditServlet userEditServlet = new UserEditServlet();
        userEditServlet.setSecurityService(securityService);
        Tomcat.addServlet(ctx, "UserEditServlet", userEditServlet);
        ctx.addServletMapping("/edituser", "UserEditServlet");
    }

    private void initDeleteUser(Context ctx) {
//        System.out.println("del");
        UserDeleteServlet userDeleteServlet = new UserDeleteServlet();
        userDeleteServlet.setSecurityService(securityService);
        Tomcat.addServlet(ctx, "UserDeleteServlet", userDeleteServlet);
        ctx.addServletMapping("/deluser", "UserDeleteServlet");
    }
}
