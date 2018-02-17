package com.j2core.sts.webcrawler.ui.filters;

import com.j2core.sts.webcrawler.ui.dto.user_dto.SecurityToken;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The filter verify user's information before forward request ( if user didn't login send to the login page )
 */
public class VerificationFilter implements Filter {

    private FilterConfig filterConfig = null;            // configuration filter

    public void init(FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        SecurityToken token = (SecurityToken) session.getAttribute("securityToken");

        if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {

            if (token != null) {

                filterChain.doFilter(servletRequest, servletResponse);
                return;

            } else {

                HttpServletRequest req = (HttpServletRequest) servletRequest;
                String[] list = req.getRequestURI().split("/");
                String page = null;

                if (list[list.length - 1].equals("LoginServlet")) {
                    page = list[list.length - 1];
                }

                if (page != null) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;

                } else {

                    ServletContext ctx = filterConfig.getServletContext();
                    RequestDispatcher dispatcher = ctx.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(servletRequest, servletResponse);
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
