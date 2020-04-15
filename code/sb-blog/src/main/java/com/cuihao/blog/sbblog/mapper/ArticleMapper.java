package com.cuihao.blog.sbblog.mapper;

import com.cuihao.blog.sbblog.domain.Article;
import com.cuihao.blog.sbblog.util.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {

    Long queryCount();

    List<Article> queryList(Page<Article> page);
}
