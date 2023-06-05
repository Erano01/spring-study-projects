package me.erano.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import me.erano.com.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
