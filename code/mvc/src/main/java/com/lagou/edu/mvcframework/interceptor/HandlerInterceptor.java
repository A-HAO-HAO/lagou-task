package com.lagou.edu.mvcframework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerInterceptor {

    boolean preHandler(HttpServletRequest request, HttpServletResponse response,Object handler);

    void postHandler(HttpServletRequest request, HttpServletResponse response);

}
