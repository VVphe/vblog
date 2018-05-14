package com.vv.blog.vblog.service;

import com.vv.blog.vblog.entity.Reply;

import java.util.List;

public interface ReplyService {

    Reply addReply(int cmid, int replyid, String replytype, String replycontent, String fromuser, String touser);

    List<Reply> getReply(int cmid);

}
