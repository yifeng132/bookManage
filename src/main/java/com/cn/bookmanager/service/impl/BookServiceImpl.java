package com.cn.bookmanager.service.impl;

import com.cn.bookmanager.domain.dto.BookQueryDto;
import com.cn.bookmanager.domain.vo.BookVO;
import com.cn.bookmanager.domain.entity.Book;
import com.cn.bookmanager.mapper.BookMapper;
import com.cn.bookmanager.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;


    @Override
    public PageInfo<BookVO> getBookList(BookQueryDto dto) {
        // 开启分页，这一行就行
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        // 自己写mapper查询，把Book转成BookVO即可
        List<Book> list = bookMapper.list(dto);
        List<BookVO> voList = list.stream()
                .map(book -> {
                    BookVO vo = new BookVO();
                    BeanUtils.copyProperties(book, vo);
                    return vo;
                })
                .collect(Collectors.toList());

        return new PageInfo<>(voList);
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
