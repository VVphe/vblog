package com.vv.blog.vblog.service.Impl;

import com.vv.blog.vblog.dao.TagDao;
import com.vv.blog.vblog.entity.Tag;
import com.vv.blog.vblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;

public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    public Tag addTag(String tagname) {
        Tag tag = tagDao.selectTagByName(tagname);
        if(tag == null) {
            tag = new Tag();
            tag.setTagname(tagname);
            tagDao.insertTag(tag);

            return tag;
        }
        return tag;
    }

    @Override
    public Tag selectTagByName(String tagname) {
        return tagDao.selectTagByName(tagname);
    }
}
