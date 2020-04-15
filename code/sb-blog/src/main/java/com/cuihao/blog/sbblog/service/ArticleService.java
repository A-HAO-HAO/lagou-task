package com.cuihao.blog.sbblog.service;

import com.cuihao.blog.sbblog.domain.Article;
import com.cuihao.blog.sbblog.util.Page;

public interface ArticleService {

    Page<Article> queryPage(Page<Article> page);

}
