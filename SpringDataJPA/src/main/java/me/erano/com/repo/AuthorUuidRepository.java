package me.erano.com.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.erano.com.domain.AuthorUuid;

@Repository
public interface AuthorUuidRepository extends JpaRepository<AuthorUuid, UUID> {
}
