package com.Library.libraryManagementSystem.Service;

import com.Library.libraryManagementSystem.Repo.BookRepository;
import com.Library.libraryManagementSystem.Repo.BorrowRepository;
import com.Library.libraryManagementSystem.Repo.PatronRepository;
import com.Library.libraryManagementSystem.model.BorrowingRecord;
import com.Library.libraryManagementSystem.model.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BorrowService {


    @Autowired
    BorrowRepository borrowRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    PatronRepository patronRepository;

    @Transactional
    public BorrowingRecord borrowBook(long bookId , long patronId) throws Exception {
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        Date current = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateFormat.format(current);
        borrowingRecord.setBorrowDate(currentDateTime);
        borrowingRecord.setBook(bookRepository.findById(bookId));
        borrowingRecord.setPatron(patronRepository.findById(patronId));
        if (borrowingRecord.getBook()==null || borrowingRecord.getPatron()==null){
            throw new Exception();
        }
        borrowingRecord.setReturnDate("");
        return borrowRepository.save(borrowingRecord);
    }

    @Transactional
    public BorrowingRecord returnBook(long bookId , long patronId) throws Exception {
        BorrowingRecord returnBook = borrowRepository.findByBookIdAndPatronId(bookId,patronId);
        if (returnBook==null)
            throw new Exception("this Book is Not Borrowed");
        Date current = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateFormat.format(current);
        returnBook.setReturnDate(currentDateTime);
        return borrowRepository.save(returnBook);
    }



}
