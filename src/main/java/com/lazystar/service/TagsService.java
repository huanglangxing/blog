package com.lazystar.service;

import com.lazystar.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagsService {

    Tag saveTag(Tag tag); //保存

    Tag getTag(Long id); //查询

    Tag getTagByName(String name);

    Page<Tag> listTag(Pageable pageable); //分页查询

    Tag updateTag(Long id,Tag tag);  //修改

    void deleteTag(Long id); //删除

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    List<Tag> listTagTop(Integer size);
}
