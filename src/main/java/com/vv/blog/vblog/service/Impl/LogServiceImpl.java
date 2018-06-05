package com.vv.blog.vblog.service.Impl;

import com.vv.blog.vblog.dao.LogDao;
import com.vv.blog.vblog.entity.Log;
import com.vv.blog.vblog.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public Log addLog(String logcontent, Date logdate) {
        Log log = new Log();
        log.setLogcontent(logcontent);
        log.setLogdate(logdate);
        logDao.insertLog(log);
        return log;
    }

    @Override
    public List<Log> getLogs() {
        return logDao.selectLogs();
    }
}
