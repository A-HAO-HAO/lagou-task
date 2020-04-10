package com.lagou.edu.auth.Interceptor;

import com.lagou.edu.auth.annotations.Security;
import com.lagou.edu.mvcframework.annotations.LagouInterceptor;
import com.lagou.edu.mvcframework.interceptor.HandlerInterceptor;
import com.lagou.edu.mvcframework.pojo.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@LagouInterceptor(path = "/.*")
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandler(HttpServletRequest request, HttpServletResponse response,Object handler) {
        Handler handlerObj = (Handler) handler;
        Object controller = handlerObj.getController();
        Method method = handlerObj.getMethod();
        Class<?> clazz = controller.getClass();
        List<Security> securities = new ArrayList<>();
        if(clazz.isAnnotationPresent(Security.class)){
            securities.add(clazz.getAnnotation(Security.class));
        }
        if(method.isAnnotationPresent(Security.class)){
            securities.add(method.getAnnotation(Security.class));
        }
        String username = request.getParameter("username");
        //检验方法和类上是否又指定鉴权用户，与请求参数的用户一致则放行没有则拦截
        for (Security security : securities) {
            String[] value = security.value();
            List<String> strings = Arrays.asList(value);
            if(!strings.contains(username)){
                try {
                    response.getWriter().println("User does not have permission");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandler(HttpServletRequest request, HttpServletResponse response) {

    }
}
