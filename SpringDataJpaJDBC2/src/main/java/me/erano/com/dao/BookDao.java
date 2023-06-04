package me.erano.com.dao;

import me.erano.com.domain.Book;

public interface BookDao {

	Book getById(Long id);
	
	Book findBookByTitle(String title);
	
	Book saveNewBook(Book book);
	
	Book updateBook(Book book);
	
	void deleteBookById(Long Id);
}
