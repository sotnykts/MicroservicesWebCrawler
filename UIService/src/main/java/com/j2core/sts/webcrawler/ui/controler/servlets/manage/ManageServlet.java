package com.j2core.sts.webcrawler.ui.controler.servlets.manage;

import com.j2core.sts.webcrawler.ui.dto.user_dto.RolesGroup;
import com.j2core.sts.webcrawler.ui.dto.user_dto.SecurityToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * The servlet define user's access level and send this information to the manage page
 */
public class ManageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        SecurityToken token = (SecurityToken) session.getAttribute("securityToken");
        List<RolesGroup> permission = token.getPermission();
        int roleId = 4;

        // define user's access level
        for (RolesGroup group : permission){

            if (group.getGroupId() < roleId) roleId = group.getGroupId();

        }

        request.setAttribute("roleId", roleId);

        // redirect user's access level to the manage page
        getServletContext().getRequestDispatcher("/manage/management.jsp").forward(request, response);

    }
}
