package com.vv.blog.vblog.controller;

import com.vv.blog.vblog.entity.Article;
import com.vv.blog.vblog.entity.ArticleTag;
import com.vv.blog.vblog.entity.Tag;
import com.vv.blog.vblog.service.ArticleService;
import com.vv.blog.vblog.service.ArticleTagService;
import com.vv.blog.vblog.service.Impl.JedisService;
import com.vv.blog.vblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private JedisService jedisService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleTagService articleTagService;

    @PostMapping("/publish")
    public Article publishArticle(@RequestParam("auth") String auth, @RequestParam("title") String title, @RequestParam("content") String content,
                           @RequestParam("description") String description, @RequestParam("category") String category, @RequestParam("tags") List<String> tags) {
        Article article = articleService.addArticle(auth, title, content, description, category);
        Article copy = articleService.getArticleByTitle(title, auth);
        for(String tag: tags) {
            Tag mTag;
            if(tagService.selectTagByName(tag) == null) {
                mTag = tagService.addTag(tag);
                Tag mTag_new = tagService.selectTagByName(tag);
                articleTagService.addArticleTag(mTag_new.getTagid(), copy.getId());
            } else {
                mTag = tagService.selectTagByName(tag);
                articleTagService.addArticleTag(mTag.getTagid(), copy.getId());
            }
        }

        return article;
    }

    @GetMapping("/delete")
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
        System.out.println("read me");
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
            if(article != null) {
                hotArticles.add(article);
            }
        }

        return hotArticles;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/readcount")
    public int readcount(@RequestParam("articleid") int articleid) {
        return jedisService.getValue("hotArticles", articleid);
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/categoryreadcount")
    public Map<String, Integer> categoryreadcount(@RequestParam("category") String category) {
        return jedisService.getCategoryValue("categoryCount", category);
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

    @GetMapping("/search")
    public List<Article> search(@RequestParam("key") String key) {
        return articleService.searchArticleByName(key);
    }

}
