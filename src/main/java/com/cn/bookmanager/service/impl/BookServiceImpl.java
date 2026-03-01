package com.cn.bookmanager.service.impl;

import com.cn.bookmanager.entity.Book;
import com.cn.bookmanager.mapper.BookMapper;
import com.cn.bookmanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> list() {
        return bookMapper.selectAll();
    }

    @Override
    public boolean add(Book book) {
        int rows = bookMapper.insert(book);
        return rows > 0;
    }

    @Override
    public boolean update(Book book) {
        int rows = bookMapper.updateById(book);
        return rows > 0;
    }

    @Override
    public boolean delete(Integer id) {
        int rows = bookMapper.deleteById(id);
        return rows > 0;
    }

    @Override
    public Book getById(Integer id) {
        return bookMapper.selectById(id);
    }
}
