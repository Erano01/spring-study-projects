package me.erano.com.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import me.erano.com.domain.composite.AuthorEmbedded;
import me.erano.com.domain.composite.NameId;

public interface AuthorEmbeddedRepository extends JpaRepository<AuthorEmbedded, NameId>{

	
}
