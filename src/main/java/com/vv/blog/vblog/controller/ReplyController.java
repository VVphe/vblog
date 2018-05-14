package com.vv.blog.vblog.controller;

import com.vv.blog.vblog.entity.Reply;
import com.vv.blog.vblog.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping("/publish")
    Reply publish(@RequestParam("cmid") int cmid, @RequestParam("replyid") int replyid, @RequestParam("replytype") String replytype,
                  @RequestParam("replycontent") String replycontent, @RequestParam("fromuser") String fromuser, @RequestParam("touser") String touser) {

        return replyService.addReply(cmid, replyid, replytype, replycontent, fromuser, touser);

    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/replies")
    List<Reply> replies(@RequestParam("cmid") int cmid) {
        return replyService.getReply(cmid);
    }

}
