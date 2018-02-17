package com.j2core.sts.webcrawler.ui.controler.servlets.user;

import com.j2core.sts.webcrawler.ui.dto.user_dto.SecurityToken;
import com.j2core.sts.webcrawler.ui.dto.user_dto.UserData;
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
 * The servlet change value user's password
 */
public class ChangeUserPassServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        SecurityToken token = (SecurityToken) session.getAttribute("securityToken");
        String new1pass = request.getParameter("new1Password");
        String new2pass = request.getParameter("new2Password");
        String login = request.getParameter("login");
        String userPass = request.getParameter("oldPassword");
        String path = ObjectContextPath.getPath();

        try {

            // if user's login or password write with mistakes servlet do redirect to the change user data's page with information about this
            if (!login.equals(token.getUserData().getLogin()) || !token.getUserData().getPassword().equalsIgnoreCase(CryptographerUserPassword.getSecurePassword(login + userPass))) {

                throw new Exception("You have mistake in login or password. Please try again.");

            } else if (new1pass.equals(new2pass)) {

                UserDAOWorker worker = new UserDAOWorker();

                UserData userData = token.getUserData();

                if (worker.changeUserInformation(userData, userData.getUserName(), userData.getLogin(), new1pass)) {

                    SecurityToken newToken = worker.getSecurityToken(login, new1pass);

                    // change user's security token with new password in the session
                    session.setAttribute("securityToken", newToken);
                    session.setMaxInactiveInterval(30 * 60);

                    // if change user's password was successfully redirect to the user's profile
                    response.sendRedirect(response.encodeRedirectURL(path + "/user/profile.jsp"));

                } else {

                    // if change user's password was unsuccessfully throw exception
                    throw new Exception("Some thing wrong with DB. Please try again.");

                }

            } else {

                // if user have mistake in the new password thow exception
                throw new Exception("You have mistake in new passwords. Please try again.");

            }
        }catch (Exception ex){

            request.setAttribute("exceptionMassage", ex.getMessage());

            // redirect to the change user password's page
            getServletContext().getRequestDispatcher("/user/changeuserpass.jsp").forward(request, response);

        }
    }

}

