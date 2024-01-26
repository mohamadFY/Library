package com.Library.libraryManagementSystem.Controllers;

import com.Library.libraryManagementSystem.Service.BorrowService;
import com.Library.libraryManagementSystem.model.BorrowingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api")
@EnableTransactionManagement
public class BorrowController {

    @Autowired
    BorrowService borrowService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<? extends Object> borrow(@PathVariable long bookId,@PathVariable long patronId) throws Exception {
        try {
            return new ResponseEntity<BorrowingRecord>(borrowService.borrowBook(bookId,patronId), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<String>("Invalid Input",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<? extends Object> returnBook(@PathVariable long bookId,@PathVariable long patronId) throws Exception {
        try {
            return new ResponseEntity<BorrowingRecord>(borrowService.returnBook(bookId, patronId), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<String>("Invalid Input",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
