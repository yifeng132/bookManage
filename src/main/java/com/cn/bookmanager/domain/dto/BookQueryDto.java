package com.cn.bookmanager.domain.dto;



import lombok.Data;

@Data
public class BookQueryDto {
    // 页码、每页条数
    private Integer pageNum = 1;
    private Integer pageSize = 10;

    // 模糊查询条件
    private String bookName;
    private String author;
}