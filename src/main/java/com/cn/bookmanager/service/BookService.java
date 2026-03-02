package com.cn.bookmanager.service;



import com.cn.bookmanager.domain.dto.BookQueryDto;
import com.cn.bookmanager.domain.vo.BookVO;
import com.cn.bookmanager.domain.entity.Book;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

@Service
public interface BookService {



    PageInfo<BookVO> getBookList(BookQueryDto dto);

    public boolean add(Book book);

    public boolean update(Book book);

    public boolean delete(Integer id);

    public Book getById(Integer id);
}
