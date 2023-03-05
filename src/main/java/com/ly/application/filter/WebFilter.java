package com.ly.application.filter;


import jakarta.servlet.*;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class WebFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("");
        RequestFacade requestFacade = (RequestFacade) request;
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
