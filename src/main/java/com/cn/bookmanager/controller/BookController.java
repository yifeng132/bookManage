package com.cn.bookmanager.controller;



import com.cn.bookmanager.common.Result;
import com.cn.bookmanager.domain.dto.BookQueryDto;
import com.cn.bookmanager.domain.entity.Book;
import com.cn.bookmanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public Result list(BookQueryDto dto) {
        return Result.success(bookService.getBookList(dto));
    }

    @PostMapping("/add")
    public Result<?> add(@RequestBody Book book) {
        // 1. 基础参数校验：确保book对象和关键字段不为空
        if (book == null) {
            return Result.error("添加失败：图书信息不能为空");
        }
        if (book.getBookName() == null || book.getBookName().trim().isEmpty()) {
            return Result.error("添加失败：图书名称不能为空");
        }

        // 2. 调用Service并判断添加结果（假设Service的add方法返回boolean）
        boolean isSuccess = bookService.add(book);
        if (isSuccess) {
            return Result.success("添加图书成功");
        } else {
            return Result.error("添加图书失败，请检查数据是否合法");
        }
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Book book) {
        // 1. 校验：更新必须传ID，且book对象不为空
        if (book == null || book.getId() == null) {
            return Result.error("更新失败：图书ID不能为空");
        }

        // 2. 先检查要更新的图书是否存在
        Book existBook = bookService.getById(book.getId());
        if (existBook == null) {
            return Result.error("更新失败：ID为" + book.getId() + "的图书不存在");
        }

        // 3. 执行更新并判断结果
        boolean isSuccess = bookService.update(book);
        if (isSuccess) {
            return Result.success("更新图书成功");
        } else {
            return Result.error("更新图书失败，请检查数据是否合法");
        }
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        // 1. 校验：删除的ID不能为空
        if (id == null) {
            return Result.error("删除失败：图书ID不能为空");
        }

        // 2. 先检查要删除的图书是否存在
        Book existBook = bookService.getById(id);
        if (existBook == null) {
            return Result.error("删除失败：ID为" + id + "的图书不存在");
        }

        // 3. 执行删除并判断结果
        boolean isSuccess = bookService.delete(id);
        if (isSuccess) {
            return Result.success("删除图书成功");
        } else {
            return Result.error("删除图书失败，请稍后重试");
        }
    }

    @GetMapping("/{id}")
    public Result<Book> getById(@PathVariable Integer id) {
        return Result.success(bookService.getById(id));
    }
}
