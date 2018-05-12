package com.vv.blog.vblog.service.Impl;

import com.vv.blog.vblog.dao.CommentDao;
import com.vv.blog.vblog.entity.Comment;
import com.vv.blog.vblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public Comment insertComment(String cmcontent, String username, int articleid) {
        Comment comment = new Comment();

        comment.setCmcontent(cmcontent);
        comment.setUsername(username);
        comment.setArticleid(articleid);
        comment.setCmdate(new Date());
        comment.setStars(0);

        commentDao.insertComment(comment);

        return comment;
    }

    @Override
    public void updateCommentsCnt(int cmid, int stars) {
        commentDao.updateCommentsCnt(cmid, stars);
    }

    @Override
    public List<Comment> selectCommentByArticleid(int articleid) {
        return commentDao.selectCommentByArticleid(articleid);
    }

    @Override
    public int selectCmCntByArticleid(int articleid) {
        return commentDao.selectCmCntByArticleid(articleid);
    }
}
