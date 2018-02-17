package com.j2core.sts.webcrawler.ui.controler.servlets.usermanage;

import com.j2core.sts.webcrawler.ui.dao.administration.AdministrationDAOWorker;
import com.j2core.sts.webcrawler.ui.dto.user_dto.SecurityToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The servlet administration change user's data
 */
public class AdminChangeUserDataServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        AdministrationDAOWorker workerDB = new AdministrationDAOWorker();

        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String pass1 = request.getParameter("pass1");
        String pass2 = request.getParameter("pass2");
        String[] roles = request.getParameterValues("role");
        String userId = request.getParameter("userId");
        String password = "";

        // if user's password haven't  mistakes or user's login and password not change
        if (pass1.equals(pass2) && ((login.length() > 0 && pass1.length() > 0) || (login.length() < 1 && pass1.length() < 1))) {

            SecurityToken token = workerDB.adminChangeUserInformation(Integer.valueOf(userId), name, login, password, roles);

            if (token != null) {

                request.setAttribute("userToken", token);
                request.setAttribute("alteredUser", true);

            } else {

                request.setAttribute("exception", true);

            }

            // redirect to the adminChangeUserData page with information about mistake or with information about successfully change user's data
            getServletContext().getRequestDispatcher("/useradministration/adminchangedata.jsp").forward(request, response);

        }else {

            request.setAttribute("exception", true);
            // redirect to the adminChangeUserData page with information about mistake
            getServletContext().getRequestDispatcher("/useradministration/adminchangeuserdata.jsp").forward(request, response);

        }
    }
}
