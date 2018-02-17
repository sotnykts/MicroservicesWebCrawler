package com.j2core.sts.webcrawler.ui.controler.servlets.accesslevel;

import com.j2core.sts.webcrawler.ui.dto.user_dto.SecurityToken;
import com.j2core.sts.webcrawler.ui.dao.user.UserDAOWorker;
import com.j2core.sts.webcrawler.ui.service.ObjectContextPath;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * The servlet get user's information from request, verify its with this user's information which saved in the DB.
 * Create Security token with user's information for use like identification on the others pages
 */
public class LoginServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        String login = request.getParameter("login");
        String userPass = request.getParameter("password");
        UserDAOWorker worker = new UserDAOWorker();

        SecurityToken securityToken = worker.getSecurityToken(login, userPass);

        HttpSession session = request.getSession();
        String path = ObjectContextPath.getPath();
        session.setAttribute("contextPath", path);

        // if user's data are correctly
        if (securityToken !=  null) {

            session.setAttribute("securityToken", securityToken);
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30*60);

            //redirect to the meeting page
            response.sendRedirect(path + "/homepage/meetingpage.jsp");

        // if user's data incorrectly or not exist in the DB
        }else {

            request.setAttribute("verifyLogin", false);

            //redirect to the first page
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

        }
    }
}
