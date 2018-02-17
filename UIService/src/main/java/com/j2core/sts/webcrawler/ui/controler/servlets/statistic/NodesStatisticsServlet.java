package com.j2core.sts.webcrawler.ui.controler.servlets.statistic;

import com.j2core.sts.webcrawler.ui.dao.statistic.StatisticDAOWorker;
import com.j2core.sts.webcrawler.ui.dto.node_dto.NodeStatistic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The servlet get node's information (with information about URL which attach to this node) which servlet take in the request
 */
public class NodesStatisticsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StatisticDAOWorker worker = new StatisticDAOWorker();
        String nodeId = request.getParameter("nodeId");
        Integer id = null;

        try {

            id = Integer.parseInt(nodeId);
        }catch (NumberFormatException e) {

            // if information in request isn't number, redirect to the statistics node's page with exception
            request.setAttribute("exception", " Your nodeId isn't number! Please try again.");
        }

        if (id !=  null) {

            NodeStatistic nodeStatistic = worker.getNodeStatistic(id);
            request.setAttribute("nodeStatistics", nodeStatistic);

        }else {

            // if id node's isn't exist in the DB
            request.setAttribute("exception", " Sorry this node's id isn't exist in data base. Please try again.");

        }

        // redirect to the statistics node's page
        getServletContext().getRequestDispatcher("/statistic/nodestatistics.jsp").forward(request, response);

    }
}
