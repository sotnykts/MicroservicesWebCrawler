package com.j2core.sts.webcrawler.ui.controler.servlets.usermanage;

import com.j2core.sts.webcrawler.ui.dao.administration.AdministrationDAOWorker;
import com.j2core.sts.webcrawler.ui.service.CryptographerUserPassword;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The servlet add new users in to the DB
 */
public class AdminAddNewUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String userPass1 = request.getParameter("pass1");
        String userPass2 = request.getParameter("pass2");
        String[] roles = request.getParameterValues("role");

        // if user's password has mistakes
        if (!userPass1.equals(userPass2)) {

            // create message with exception
            request.setAttribute("exception", "Mistake in the user's password. Please try again.");

        } else {

            String password = CryptographerUserPassword.getSecurePassword(login + userPass1);
            AdministrationDAOWorker workerDB = new AdministrationDAOWorker();
            boolean result = workerDB.addNewUser(name, login, password, roles);

            if (result){
                request.setAttribute("newUser", true);
            }else {
                request.setAttribute("exception", "Some thing wrong with DB. Please try again.");
            }

        }

        // redirect to the adminAddNewUser page with exceptions messages or information about successfully add new user to the DB
        getServletContext().getRequestDispatcher("/useradministration/adminaddnewuser.jsp").forward(request, response);

    }
}

