package com.example.wselsfk.conffig;


import javax.servlet.*;
import java.io.IOException;

/**
 * Created by ZhaoLai Huang on 2018/4/16.
 */
public class CrosFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse res = (HttpServletResponse)servletResponse;
//        res.addHeader("Access-Control-Allow-Origin","*");
//        res.addHeader("Access-Control-Allow-Methonds","*");
//        res.addHeader("Access-Control-Max-Age","3600");
//        HttpServletRequest request = (HttpServletRequest)servletRequest;
//        String headers = request.getHeader("Access-Control-Allow-Headers");
//        if(StringUtils.isNotBlank(headers)){
//            res.addHeader("Access-Control-Allow-Headers",headers);
//        }
//        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
