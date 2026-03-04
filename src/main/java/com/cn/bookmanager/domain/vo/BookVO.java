package com.cn.bookmanager.domain.vo;



import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookVO {
    private Integer id;
    private String bookName;
    private String author;
    private Double price;
    private LocalDateTime createTime;
}
