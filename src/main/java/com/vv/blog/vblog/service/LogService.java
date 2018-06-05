package com.vv.blog.vblog.service;

import com.vv.blog.vblog.entity.Log;

import java.util.Date;
import java.util.List;

public interface LogService {

    Log addLog(String logcontent, Date logdate);

    List<Log> getLogs();

}
