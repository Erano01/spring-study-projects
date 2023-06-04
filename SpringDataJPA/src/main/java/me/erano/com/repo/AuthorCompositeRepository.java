package me.erano.com.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import me.erano.com.domain.composite.AuthorComposite;
import me.erano.com.domain.composite.NameId;

public interface AuthorCompositeRepository extends JpaRepository<AuthorComposite, NameId> {
}
