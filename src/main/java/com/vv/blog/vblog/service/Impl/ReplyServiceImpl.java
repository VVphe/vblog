package com.vv.blog.vblog.service.Impl;

import com.vv.blog.vblog.dao.ReplyDao;
import com.vv.blog.vblog.entity.Reply;
import com.vv.blog.vblog.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService{

    @Autowired
    private ReplyDao replyDao;


    @Override
    public Reply addReply(int cmid, int replyid, String replytype, String replycontent, String fromuser, String touser) {
        Reply reply = new Reply();
        reply.setCmid(cmid);
        reply.setReplyid(replyid);
        reply.setReplytype(replytype);
        reply.setReplycontent(replycontent);
        reply.setFromuser(fromuser);
        reply.setTouser(touser);

        replyDao.insertReply(reply);

        return reply;
    }

    @Override
    public List<Reply> getReply(int cmid) {
        return replyDao.selectReply(cmid);
    }
}
