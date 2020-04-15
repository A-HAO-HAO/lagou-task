package com.cuihao.blog.sbblog.controller;

import com.cuihao.blog.sbblog.domain.Article;
import com.cuihao.blog.sbblog.service.ArticleService;
import com.cuihao.blog.sbblog.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/index")
    public String index(Model model){
        return article(1,model);
    }

    @RequestMapping("/index/{pageNo}")
    public String article(@PathVariable Integer pageNo, Model model){
        Page<Article> page = new Page<>();
        page.setPageSize(2);
        page.setPageNo(pageNo);
        page.setPageNo(pageNo);
        Page<Article> articlePage = articleService.queryPage(page);
        model.addAttribute("page",articlePage);
        return "client/index";
    }

}
