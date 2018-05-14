package com.vv.blog.vblog.controller;

import com.vv.blog.vblog.entity.Article;
import com.vv.blog.vblog.entity.ArticleTag;
import com.vv.blog.vblog.service.ArticleService;
import com.vv.blog.vblog.service.Impl.JedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private JedisService jedisService;

    @PostMapping("/publish")
    public Article publish(@RequestParam("auth") String auth, @RequestParam("title") String title, @RequestParam("content") String content,
                           @RequestParam("description") String description, @RequestParam("category") String category) {
        return articleService.addArticle(auth, title, content, description, category);
    }

    @PostMapping("/delete")
    public Article delete(@RequestParam("id") int id) {
        return articleService.deleteArticle(id);
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/category")
    public List<Article> category(@RequestParam("username") String username, @RequestParam("category") String category) {
        return articleService.selectArticleByCategoty(username, category);
    }

    @GetMapping("/page")
    public List<Article> pageArticles(@RequestParam("pageid") int pageid) {
        return articleService.selectPageArticle(pageid, 20);
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/readarticle")
    public Article getArticle(@RequestParam("articleid") int articleid) {
        return articleService.getArticleById(articleid);
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/hotestarticle")
    public List<Article> gethotestarticle(@RequestParam("start") int start, @RequestParam("end") int end) {
        List<Article> hotArticles = new ArrayList<>();
        Set<String> set = jedisService.zrevrange("hotArticles", start, end);
        for(String s : set) {
            int articleid = Integer.parseInt(s.split(":")[1]);
            Article article = articleService.getArticleById(articleid);
            hotArticles.add(article);
        }

        return hotArticles;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/readcount")
    public int readcount(@RequestParam("articleid") int articleid) {
        return jedisService.getValue("hotArticles", articleid);
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/newestarticle")
    public List<Article> getnewestarticle(@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        return articleService.getNewestArticle(offset, limit);
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/count")
    public int count() {
        return articleService.getArticleCnt();
    }

    @GetMapping("/categorycnt")
    public int categorycnt(@RequestParam("category") String category) {
        return articleService.getCategoryArticleCnt(category);
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping("/updatecomment")
    public void updatecomment(@RequestParam("articleid") int articleid, @RequestParam("commentsCnt") int commentsCnt) {
        articleService.updateCommentsCnt(articleid, commentsCnt);
    }

}
