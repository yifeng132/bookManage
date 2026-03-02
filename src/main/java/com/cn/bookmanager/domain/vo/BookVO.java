package com.cn.bookmanager.domain.vo;



import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookVO {
    private Long id;
    private String bookName;
    private String author;
    private String price;
    private LocalDateTime createTime;
}
