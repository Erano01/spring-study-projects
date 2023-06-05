package me.erano.com.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import me.erano.com.domain.Author;



public interface AuthorRepository extends JpaRepository<Author, Long> {
}
