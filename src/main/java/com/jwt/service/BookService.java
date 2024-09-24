package com.jwt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.exception.InvalidIsbnException;
import com.jwt.model.Book;
import com.jwt.repo.BookRepository;

@Service
public class BookService {

	  @Autowired
	   private BookRepository bookRepository;
	  
	  
	  public Book addBook(Book book) {
	        return bookRepository.save(book); 
	    }
	  
	  public List<Book> getAllBooks() {
	        return bookRepository.findAll();
	    }
	  
	  public Optional<Book> getBookByIsbn(String isbn) {
		    return bookRepository.findByIsbn(isbn);
		}
	  
	  
	  public void updateBook(Book book) {
		    bookRepository.save(book); 
		}
	  
	  public void deleteBook(Book book) {
		    bookRepository.delete(book);
		}

}
