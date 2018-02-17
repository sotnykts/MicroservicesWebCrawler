package com.j2core.sts.webcrawler.ui.controler.servlets.manage;

import com.j2core.sts.webcrawler.ui.dao.administration.AdministrationDAOWorker;
import com.j2core.sts.webcrawler.ui.dto.node_dto.NodeStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * The servlet get information about all nodes from DB and redirect this information to the manage node's page
 */
public class NodeInformationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {

        AdministrationDAOWorker worker = new AdministrationDAOWorker();

        //get node's information from DB
        List<NodeStatus> nodeStatisticList = worker.getNodeStatus();

        request.setAttribute("nodeStatus", nodeStatisticList);

        // redirect node's information to the manage node's page
        getServletContext().getRequestDispatcher("/manage/nodesmanage.jsp").forward(request, response);

    }
}
