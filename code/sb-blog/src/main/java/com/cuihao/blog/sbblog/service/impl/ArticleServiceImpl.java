package com.cuihao.blog.sbblog.service.impl;

import com.cuihao.blog.sbblog.domain.Article;
import com.cuihao.blog.sbblog.mapper.ArticleMapper;
import com.cuihao.blog.sbblog.service.ArticleService;
import com.cuihao.blog.sbblog.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Page<Article> queryPage(Page<Article> page) {
        Long totalCount = articleMapper.queryCount();
        List<Article> articles = articleMapper.queryList(page);
        page.setTotoalCount(totalCount);
        page.setDataList(articles);
        return page;
    }
}
