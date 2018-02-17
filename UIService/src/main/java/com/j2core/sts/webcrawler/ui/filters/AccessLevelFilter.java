package com.j2core.sts.webcrawler.ui.filters;

import com.j2core.sts.webcrawler.ui.dto.user_dto.RolesGroup;
import com.j2core.sts.webcrawler.ui.dto.user_dto.SecurityToken;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The filter check user's access level before forward request next
 */
public class AccessLevelFilter implements Filter{

    private FilterConfig filterConfig = null;           // configuration filter
    private static ArrayList<String> adminPage;         // list with administration's pages

    public AccessLevelFilter(){

        if (adminPage == null){

            adminPage = new ArrayList<String>();
            adminPage.add("usermanage.jsp");
            adminPage.add("adminaddnewuser.jsp");
            adminPage.add("adminchangedata.jsp");
            adminPage.add("admindeleteuser.jsp");

        }

    }

    public void init(FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {

            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpSession session = request.getSession();
            SecurityToken token = (SecurityToken) session.getAttribute("securityToken");

            if (token != null) {

                List<RolesGroup> permission = token.getPermission();

                int roleId = 3;
                for (RolesGroup group : permission) {

                    if (group.getGroupId() < roleId) {
                        roleId = group.getGroupId();
                    }
                }

                String[] list = request.getRequestURI().split("/");
                String page = null;
                if (list[list.length - 1].indexOf(".jsp") > 0) {
                    page = list[list.length - 1];
                }

                if (page != null && ((adminPage.contains(page) && roleId != 1) || (page.equals("nodesmanage.jsp") && roleId > 2))) {

                    ServletContext ctx = filterConfig.getServletContext();
                    RequestDispatcher dispatcher = ctx.getRequestDispatcher("/management.jsp");
                    dispatcher.forward(servletRequest, servletResponse);
                    return;

                } else {

                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    public void destroy() {

        filterConfig = null;

    }
}
