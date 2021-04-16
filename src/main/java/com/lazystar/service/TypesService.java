package com.lazystar.service;

import com.lazystar.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface TypesService {

    Type saveType(Type type); //保存

    Type getType(Long id); //查询

    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable); //分页查询

    Type updateType(Long id,Type type);  //修改

    void deleteType(Long id); //删除

    List<Type> listType(); //获取所有分类

    List<Type> listTypeTop(Integer size);
}
