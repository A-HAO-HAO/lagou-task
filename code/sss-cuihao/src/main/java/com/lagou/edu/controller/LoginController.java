package com.lagou.edu.controller;

import com.lagou.edu.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/login-controller")
public class LoginController {

    private static List<User> userList = new ArrayList<>();

    static {
        userList.add(new User("admin","admin"));
    }

    @RequestMapping("")
    public String index(){
        return "/login";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, User user) throws IOException {
        for (User u : userList) {
            if(u.getUserName().equalsIgnoreCase(user.getUserName())
                    && u.getPassword().equalsIgnoreCase(user.getPassword())){
                request.getSession().setAttribute("user",user);
                return "redirect:/resume";
            }
        }
        request.getSession().setAttribute("info","用户名或密码错误");
        return "redirect:/";
    }

}
