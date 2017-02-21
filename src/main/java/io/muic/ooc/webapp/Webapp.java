package io.muic.ooc.webapp;

import io.muic.ooc.webapp.service.SecurityService;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ErrorPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.File;


public class Webapp {

    public static void main(String[] args) {

        String docBase = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(80);
        // Databaseservice
//        DatabaseService databaseService = new DatabaseService();
        SecurityService securityService = new SecurityService();

        ServletRouter servletRouter = new ServletRouter();
        servletRouter.setSecurityService(securityService);
//        servletRouter.setDatbaseService(databaseService);


        ErrorPage errorPage = new ErrorPage();
        errorPage.setErrorCode(HttpServletResponse.SC_NOT_FOUND);
        errorPage.setLocation("/");
        Context ctx;

        try {
            ctx = tomcat.addWebapp("/", new File(docBase).getAbsolutePath());
            servletRouter.init(ctx);
            ctx.addErrorPage(errorPage);
            tomcat.start();
            tomcat.getServer().await();
        } catch (ServletException | LifecycleException ex) {
            ex.printStackTrace();
        }

    }
}
