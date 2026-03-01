package com.cn.bookmanager.mapper;



import com.cn.bookmanager.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> selectAll();
    int insert(Book book);
    int updateById(Book book);
    int deleteById(Integer id);
    Book selectById(Integer id);
}
