package com.j2core.sts.webcrawler.ui.controler.servlets.user;

import com.j2core.sts.webcrawler.ui.dto.user_dto.SecurityToken;
import com.j2core.sts.webcrawler.ui.dao.user.UserDAOWorker;
import com.j2core.sts.webcrawler.ui.service.CryptographerUserPassword;
import com.j2core.sts.webcrawler.ui.service.ObjectContextPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

/**
 * The servlet change user data
 */
public class ChangeUserDataServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        SecurityToken token =(SecurityToken) session.getAttribute("securityToken");
        String newUserName = request.getParameter("newName");
        String newLogin = request.getParameter("newLogin");
        String login = request.getParameter("oldLogin");
        String userPass = request.getParameter("password");

        String path = ObjectContextPath.getPath();

        UserDAOWorker worker = new UserDAOWorker();

        // if user's login or password write with mistakes servlet do redirect to the change user data's page with information about this
        if (!login.equals(token.getUserData().getLogin()) || !token.getUserData().getPassword().equalsIgnoreCase(CryptographerUserPassword.getSecurePassword(login + userPass))) {

            request.setAttribute("verifyUserData", false);

            getServletContext().getRequestDispatcher("/user/changeuserdata.jsp").forward(request, response);

        } else {

            if (worker.changeUserInformation(token.getUserData(), newUserName, newLogin, userPass)) {

                // if change user's information was successfully change security token in the session
                SecurityToken newToken = worker.getSecurityToken(newLogin, userPass);

                session.setAttribute("securityToken", newToken);
                session.setMaxInactiveInterval(30 * 60);
            }

            // redirect to the profile's page
            response.sendRedirect(response.encodeRedirectURL(path + "/user/profile.jsp"));

        }
    }
}
