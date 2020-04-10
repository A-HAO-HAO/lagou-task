package com.lagou.demo.controller;

import com.lagou.demo.service.IDemoService;
import com.lagou.edu.auth.annotations.Security;
import com.lagou.edu.mvcframework.annotations.LagouAutowired;
import com.lagou.edu.mvcframework.annotations.LagouController;
import com.lagou.edu.mvcframework.annotations.LagouRequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@LagouController
@LagouRequestMapping("/demo")
@Security({"zhangsan","lisi","wangwu"})
public class DemoController {


    @LagouAutowired
    private IDemoService demoService;

    @LagouRequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().println("zs,ls,ww");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return demoService.get("zs,ls,ww");
    }

    /**
     * URL: /demo/query?name=lisi
     * @param request
     * @param response
     * @param name
     * @return
     */
    @LagouRequestMapping("/query/zs")
    @Security({"zhangsan"})
    public String queryZs(HttpServletRequest request, HttpServletResponse response,String name) {
        try {
            response.getWriter().println(name+"----zs");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return demoService.get(name+"----zs");
    }

    @LagouRequestMapping("/query/ls")
    @Security({"lisi"})
    public String queryLs(HttpServletRequest request, HttpServletResponse response,String name) {
        try {
            response.getWriter().println(name+"----ls");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return demoService.get(name+"----ls");
    }
}
