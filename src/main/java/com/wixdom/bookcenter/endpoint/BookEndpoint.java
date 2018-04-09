package com.wixdom.bookcenter.endpoint;

import com.wixdom.bookcenter.domain.Book;
import com.wixdom.bookcenter.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Henry Lin badcop@163.com
 */
@RestController
@RequestMapping("api/book")
@Api(tags = "Book")
public class BookEndpoint {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "create book", notes = "save book to database", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(path = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean create(@RequestBody Book book) {
        return bookService.save(book);
    }

    @ApiOperation(value = "get book", notes = "get book by id", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book findOneById(@PathVariable Long id) {
        return bookService.findOneById(id);
    }

    @ApiOperation(value = "update book", notes = "update book in database", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    @PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        return bookService.update(book);
    }

    @ApiOperation(value = "delete book", notes = "remove book from database", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    @DeleteMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean delete(@PathVariable Long id) {
        return bookService.delete(id);
    }
}
