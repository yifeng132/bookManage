package com.cn.bookmanager.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Book {
    private Integer id;
    private String bookName;
    private String author;
    private Double price;
    private LocalDateTime createTime;
}
