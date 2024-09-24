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
	  
	  
	  public Book updateBookDetails(String isbn, Book updatedBook) throws InvalidIsbnException {
		    Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
		    
		    if (optionalBook.isEmpty()) {
		        throw new InvalidIsbnException("Book not found with ISBN: " + isbn);
		    }
		    
		    Book book = optionalBook.get();
		    book.setTitle(updatedBook.getTitle());
		    book.setAuthor(updatedBook.getAuthor());
		    book.setPublicationYear(updatedBook.getPublicationYear());
		    
		    return bookRepository.save(book);
		}
	  
	  public void deleteBookByIsbn(String isbn) throws InvalidIsbnException {
		    Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
		    
		    if (optionalBook.isEmpty()) {
		        throw new InvalidIsbnException("Book not found with ISBN: " + isbn);
		    }
		    
		    Book book = optionalBook.get();
		    bookRepository.delete(book);
		}

}
