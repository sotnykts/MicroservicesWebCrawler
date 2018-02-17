package com.j2core.sts.webcrawler.ui.controler.servlets.statistic;

import com.j2core.sts.webcrawler.ui.dao.statistic.StatisticDAOWorker;
import com.j2core.sts.webcrawler.ui.dto.node_dto.AllNodeStatistic;
import com.j2core.sts.webcrawler.ui.dto.url_dto.UrlStatistic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The servlet get all node's statistics and statistics about URL from DB.
 */
public class StatisticServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {

        StatisticDAOWorker worker = new StatisticDAOWorker();
        UrlStatistic urlStatistic = worker.getUrlStatistic();
        AllNodeStatistic allNodeStatistic = worker.getNodesStatistics();

        // node's statistics
        request.setAttribute("nodeStatistics", allNodeStatistic.getNodeDataList());
        // amount start node
        request.setAttribute("startedNode", allNodeStatistic.getStartedNode());
        // amount stop node
        request.setAttribute("stoppedNode", allNodeStatistic.getStoppedNode());
        // URL' s statistics like : amount not processed, processes, and processed URLs
        request.setAttribute("urlStatistic", urlStatistic);

        // redirect to the web crawler's statistics page
        getServletContext().getRequestDispatcher("/statistic/statistic.jsp").forward(request, response);

    }
}
