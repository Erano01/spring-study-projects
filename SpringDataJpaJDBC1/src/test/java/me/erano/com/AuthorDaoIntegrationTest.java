package me.erano.com;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import me.erano.com.dao.AuthorDao;
import me.erano.com.dao.AuthorDaoImpl;
import me.erano.com.dao.BookDao;
import me.erano.com.dao.BookDaoImpl;
import me.erano.com.domain.Author;
import me.erano.com.domain.Book;

@ActiveProfiles("local")
@DataJpaTest
@Import({AuthorDaoImpl.class, BookDaoImpl.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoIntegrationTest {
	
	@Autowired
	AuthorDao authorDao;
	
	@Autowired
    BookDao bookDao;

    @Test
    void testDeleteBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        
        Author author = new Author();
        author.setId(3L);
        book.setAuthor(author);
        
        Book saved = bookDao.saveNewBook(book);

        bookDao.deleteBookById(saved.getId());

        Book deleted = bookDao.getById(saved.getId());

        assertThat(deleted).isNull();
    }

    @Test
    void updateBookTest() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        
        Author author = new Author();
        author.setId(3L);
        book.setAuthor(author);
        
        Book saved = bookDao.saveNewBook(book);

        saved.setTitle("New Book");
        bookDao.updateBook(saved);

        Book fetched = bookDao.getById(saved.getId());

        assertThat(fetched.getTitle()).isEqualTo("New Book");
    }

    @Test
    void testSaveBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        
        Author author = new Author();
        author.setId(3L);
        book.setAuthor(author);
        
        Book saved = bookDao.saveNewBook(book);

        assertThat(saved).isNotNull();
    }

    @Test
    void testGetBookByName() {
        Book book = bookDao.findBookByTitle("Clean Code");

        assertThat(book).isNotNull();
    }

    @Test
    void testGetBook() {
        Book book = bookDao.getById(3L);

        assertThat(book.getId()).isNotNull();
    }

	
	@Test
	void testUpdateAuthor() {
		Author author = new Author();
		author.setFirstName("John");
		author.setLastName("t");
		
		Author saved = authorDao.saveNewAuthor(author);
		
		saved.setLastName("Thompson");
		Author updated = authorDao.updateAuthor(saved);
		
		assertThat(updated.getLastName()).isEqualTo("Thompson");
	}
	
	@Test
	void testGetAuthorById() {
		Author author = authorDao.getById(1L);
		assertThat(author).isNotNull();
	}
	@Test
	void testGetAuthorByFullName() {
		Author author = authorDao.findAuthorByName("Craig","Walls");
		assertThat(author).isNotNull();
	}
	
	@Test
	void testSaveAuthor() {
		Author author = new Author();
		author.setFirstName("John");
		author.setLastName("Thompson");
		Author saved = authorDao.saveNewAuthor(author);
		
		assertThat(saved).isNotNull();
		
	}
	@Test
	void testDeleteAuthor() {
		Author author = new Author();
		author.setFirstName("John");
		author.setLastName("t");
		
		Author saved = authorDao.saveNewAuthor(author);
		
		authorDao.deleteAuthorById(saved.getId());
		
		Author deleted = authorDao.getById(saved.getId());
		
		assertThat(deleted).isNull();
	}
	

}
