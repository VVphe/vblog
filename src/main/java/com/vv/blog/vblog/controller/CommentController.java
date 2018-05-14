package com.vv.blog.vblog.controller;

import com.vv.blog.vblog.entity.Comment;
import com.vv.blog.vblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping("/publish")
    public Comment publish(@RequestParam("cmcontent") String cmcontent, @RequestParam("username") String username,
                           @RequestParam("articleid") int articleid) {
        return commentService.insertComment(cmcontent, username, articleid);
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/count")
    public int count(@RequestParam("articleid") int articleid) {
        return commentService.selectCmCntByArticleid(articleid);
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/comments")
    public List<Comment> comments(@RequestParam("articleid") int articleid) {
        return commentService.selectCommentByArticleid(articleid);
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping("/updatestars")
    public void updateStars(@RequestParam("cmid") int cmid, @RequestParam("stars") int stars) {
        commentService.updateCommentsCnt(cmid, stars);
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/page")
    public List<Comment> pageComments(@RequestParam("articleid") int articleid, @RequestParam("limit") int limit) {
        return commentService.selectPageComment(articleid, 0, limit);
    }

}
