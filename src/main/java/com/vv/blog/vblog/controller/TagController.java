package com.vv.blog.vblog.controller;

import com.vv.blog.vblog.entity.Tag;
import com.vv.blog.vblog.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private ArticleTagService articleTagService;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/articletag")
    public List<Tag> articletag(int articleid) {
        return articleTagService.selectTagsOfArticle(articleid);
    }

}
