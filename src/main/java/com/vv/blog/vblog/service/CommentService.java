package com.vv.blog.vblog.service;

import com.vv.blog.vblog.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment insertComment(String cmcontent, String username, int articleid);

    void updateCommentsCnt(int cmid, int stars);

    List<Comment> selectCommentByArticleid(int articleid);

    int selectCmCntByArticleid(int articleid);

    List<Comment> selectPageComment(int articleid, int offset, int limit);

}
