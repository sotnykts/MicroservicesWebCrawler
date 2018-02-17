package com.j2core.sts.webcrawler.ui.controler.servlets.usermanage;

import com.j2core.sts.webcrawler.ui.dao.administration.AdministrationDAOWorker;
import com.j2core.sts.webcrawler.ui.dto.user_dto.SecurityToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The servlet find user's information
 */
public class AdminFindUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        String nextJsp = request.getParameter("nextJsp");
        String backJsp = request.getParameter("backJsp");
        String userId = request.getParameter("userId");
        String login = request.getParameter("login");
        String name = request.getParameter("name");

        AdministrationDAOWorker workerDB = new AdministrationDAOWorker();

        // user's information which need find
        SecurityToken userToken = workerDB.findUserInformation(userId, login, name);

        if (userToken != null) {

            request.setAttribute("userToken", userToken);
            // redirect user's information to the page which do this request
            getServletContext().getRequestDispatcher(nextJsp).forward(request, response);


        } else {

            request.setAttribute("exception", true);
            // redirect exception's information to the page which do this request
            getServletContext().getRequestDispatcher(backJsp).forward(request, response);

        }
    }
}
