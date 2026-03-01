package com.cn.bookmanager.service;



import com.cn.bookmanager.entity.Book;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface BookService {



    public List<Book> list();

    public boolean add(Book book);

    public boolean update(Book book);

    public boolean delete(Integer id);

    public Book getById(Integer id);
}
