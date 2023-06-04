package me.erano.com.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.erano.com.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
