package com.Library.libraryManagementSystem.Service;

import com.Library.libraryManagementSystem.Repo.BookRepository;
import com.Library.libraryManagementSystem.Repo.BorrowRepository;
import com.Library.libraryManagementSystem.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowRepository borrowRepository;

    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }

    public Book getBookById(long id){
        return bookRepository.findById(id);
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public Book updateBook(long id ,Book newBook){
        Book book = bookRepository.findById(id);
        book.setAuthor(newBook.getAuthor());
        book.setIsbn(newBook.getIsbn());
        book.setTitle(newBook.getTitle());
        book.setPublicationYear(newBook.getPublicationYear());
        return bookRepository.save(book);
    }

    public boolean deleteBook(long id) throws Exception {
        if (borrowRepository.findByBookId(id)==null) {
            if (bookRepository.findById(id) != null) {
                bookRepository.deleteById(id);
                return true;
            }
            else {
                throw new Exception("Book Not Found");
            }
        }
        else {
            throw new Exception("Cant Delete Book");
        }
    }


}
