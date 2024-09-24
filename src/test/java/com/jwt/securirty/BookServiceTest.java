package com.jwt.securirty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.jwt.model.Book;
import com.jwt.repo.BookRepository;
import com.jwt.service.BookService;

@SpringBootTest
public class BookServiceTest {
	

	 @InjectMocks
	 private BookService bookService;
	 
	 @Mock
	 private BookRepository bookRepository;
	
	
	 @Test
	 public void addBookTest() {
		 Book b1 = new Book(1, "12378", "Title1", "Author1", 2020);
	     Book b2 = new Book(2, "12379", "Title2", "Author2", 2021);
	        
	
	     when(bookService.addBook(b1)).thenReturn(b1);
	     when(bookService.addBook(b2)).thenReturn(b2);
	        
	
	     Book addedBook1 = bookService.addBook(b1);
	     Book addedBook2 = bookService.addBook(b2);
	        

	     assertNotNull(addedBook1);
	     assertNotNull(addedBook2);
	        
	     assertEquals("Title1", addedBook1.getTitle());
	     assertEquals("Author1", addedBook1.getAuthor());
	     assertEquals(2020, addedBook1.getPublicationYear());
	        
	     assertEquals("Title2", addedBook2.getTitle());
	     assertEquals("Author2", addedBook2.getAuthor());
	     assertEquals(2021, addedBook2.getPublicationYear());
	    }
	 
 
	 

}
