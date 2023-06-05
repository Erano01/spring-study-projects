package me.erano.com.dao;

import java.util.List;

import me.erano.com.domain.Author;

/**
 * Created by jt on 8/22/21.
 */
public interface AuthorDao {
	
	Author findAuthorByNameCriteria(String firstName,String lastName);
	
	List<Author> findAll();
	
	List<Author> listAuthorByLastNameLike(String lastName);
	
    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);
    
    Author findAuthorByNameNative(String firstName, String lastName);
}
