package me.erano.com.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import me.erano.com.domain.BookNatural;

public interface BookNaturalRepository extends JpaRepository<BookNatural, String> {

}
