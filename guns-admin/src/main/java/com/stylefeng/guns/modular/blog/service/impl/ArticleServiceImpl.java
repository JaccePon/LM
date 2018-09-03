package com.stylefeng.guns.modular.blog.service.impl;

import com.stylefeng.guns.common.persistence.model.Article;
import com.stylefeng.guns.common.persistence.dao.ArticleMapper;
import com.stylefeng.guns.modular.blog.service.IArticleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JaccePon123
 * @since 2018-08-24
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
