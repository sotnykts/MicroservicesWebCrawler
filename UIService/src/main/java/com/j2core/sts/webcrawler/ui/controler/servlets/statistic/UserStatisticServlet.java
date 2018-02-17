package com.j2core.sts.webcrawler.ui.controler.servlets.statistic;

import com.j2core.sts.webcrawler.ui.dao.statistic.StatisticDAOWorker;
import com.j2core.sts.webcrawler.ui.dto.user_dto.RolesGroup;
import com.j2core.sts.webcrawler.ui.dto.user_dto.UserStatistic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * The servlet get all user's information and information about user's roles in the application
 */
public class UserStatisticServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        StatisticDAOWorker worker = new StatisticDAOWorker();

        // get user's information
        List<UserStatistic> userStatisticList = worker.getUserStatistic();

        // get information about user's roles in the application
        List<RolesGroup> rolesGroupList = worker.getRolesGroup();

        request.setAttribute("userStatistics", userStatisticList);
        request.setAttribute("rolesGroup", rolesGroupList);

        // redirect to the user's statistics page
        getServletContext().getRequestDispatcher("/statistic/userstatistics.jsp").forward(request, response);

    }
}
