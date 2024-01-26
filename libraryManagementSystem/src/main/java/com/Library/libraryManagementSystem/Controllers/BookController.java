package com.Library.libraryManagementSystem.Controllers;

import com.Library.libraryManagementSystem.Service.BookService;
import com.Library.libraryManagementSystem.model.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public ResponseEntity<List<Book>> allBooks(){
        return new ResponseEntity<List<Book>>(bookService.getAllBook(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> BookById(@PathVariable long id){
        return new ResponseEntity<Book>(bookService.getBookById(id),HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Book> addBook(@RequestBody @Valid Book book){
        return new ResponseEntity<Book>(bookService.addBook(book),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id , @RequestBody @Valid Book book){
        return new ResponseEntity<Book>(bookService.updateBook(id,book),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<? extends Object> deleteById(@PathVariable long id) throws Exception {
        try {
            return new ResponseEntity<Boolean>(bookService.deleteBook(id),HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<String>("CAN'T DELETE BOOK",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
