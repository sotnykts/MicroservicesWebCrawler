package com.j2core.sts.webcrawler.ui.controler.servlets.accesslevel;

import com.j2core.sts.webcrawler.ui.service.ObjectContextPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The servlet clean session's information and redirect to the first page
 */
public class LogoutServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = ObjectContextPath.getPath();
        HttpSession session=request.getSession();
        // clean session's information
        session.invalidate();

        //redirect to the first page( page with login form)
        response.sendRedirect(response.encodeRedirectURL(path + "/index.jsp"));

    }

}
