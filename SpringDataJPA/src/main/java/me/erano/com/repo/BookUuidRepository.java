package me.erano.com.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import me.erano.com.domain.BookUuid;

public interface BookUuidRepository extends JpaRepository<BookUuid, UUID>{

}
