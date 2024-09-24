package com.jwt.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	 Optional<Book> findByIsbn(String isbn);

}
