package com.vv.blog.vblog.service;

import com.vv.blog.vblog.entity.Tag;

public interface TagService {
    Tag addTag(String tagname);

    Tag selectTagByName(String tagname);
}
