package com.jwt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.exception.InvalidIsbnException;
import com.jwt.model.Book;
import com.jwt.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	@PostMapping("/add/book")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return ResponseEntity.ok(savedBook);
    }
	
	 @GetMapping("/all-books")
	 public ResponseEntity<List<Book>> getAllBooks() {
		 List<Book> books = bookService.getAllBooks();
	     return new ResponseEntity<>(books, HttpStatus.OK);
	    }
	 
	 
	 @GetMapping("book-by-isbn/{isbn}")
	 public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
		 Optional<Book> book = bookService.getBookByIsbn(isbn);
	     return book.map(ResponseEntity::ok)
	    		 .orElseGet(() -> ResponseEntity.notFound().build());
	    }
	 
	 @PutMapping("update-book/{isbn}")
	 public ResponseEntity<Book> putMethodName(@PathVariable String isbn, @RequestBody Book updatedBook) throws InvalidIsbnException {
		 Book book = bookService.getBookByIsbn(isbn)
		            .orElseThrow(() -> new InvalidIsbnException("Book not found with ISBN: " + isbn));
		 
		 book.setTitle(updatedBook.getTitle());
		 book.setAuthor(updatedBook.getAuthor());
		 book.setPublicationYear(updatedBook.getPublicationYear());
		 bookService.updateBook(book);
		 
		 return ResponseEntity.ok(book);
	 }
	 
	 @DeleteMapping("/delete-book/{isbn}")
	 public ResponseEntity<?> deleteBook(@PathVariable String isbn) throws InvalidIsbnException {

	     Book book = bookService.getBookByIsbn(isbn)
	             .orElseThrow(() -> new InvalidIsbnException("Book not found with ISBN: " + isbn));
	     bookService.deleteBook(book);

	     return ResponseEntity.ok("Book with ISBN: " + isbn + " deleted successfully.");
	 }

}
