package com.j2core.sts.webcrawler.ui.controler.servlets.usermanage;

import com.j2core.sts.webcrawler.ui.dao.administration.AdministrationDAOWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The servlet delete user's information from DB
 */
public class AdminDeleteUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        String userId = request.getParameter("userId");
        AdministrationDAOWorker workerDB = new AdministrationDAOWorker();

        boolean result = workerDB.deleteUser(Integer.valueOf(userId));

        if (result){

            request.setAttribute("remoteUser", true);

        }else {

            request.setAttribute("exception", true);

        }

        // redirect to the adminDeleteUser page with exception or with successfully parameter
        getServletContext().getRequestDispatcher("/useradministration/admindeleteuser.jsp").forward(request, response);

    }
}
