package com.j2core.sts.webcrawler.ui.controler.servlets.manage;

import com.j2core.sts.webcrawler.ui.dao.administration.AdministrationDAOWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The servlet stop node which id take from request
 */
public class StopNodeServlet  extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        AdministrationDAOWorker workerDB = new AdministrationDAOWorker();
        String nodeId = request.getParameter("nodeId");
        Integer id = 0;

        try {

            id = Integer.parseInt(nodeId);
        }catch (NumberFormatException e) {
            e.getStackTrace();
        }

        HttpSession session = request.getSession();

        if (workerDB.stopNode(id)){

            session.setAttribute("stopNode", true);

        }else {

            session.setAttribute("stopNode", false);

        }

        // redirect to the manage node's page
        response.sendRedirect("nodesmanage.jsp");

    }
}
