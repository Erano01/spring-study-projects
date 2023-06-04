package me.erano.com;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import me.erano.com.domain.AuthorUuid;
import me.erano.com.domain.BookNatural;
import me.erano.com.domain.BookUuid;
import me.erano.com.domain.composite.AuthorComposite;
import me.erano.com.domain.composite.AuthorEmbedded;
import me.erano.com.domain.composite.NameId;
import me.erano.com.repo.AuthorCompositeRepository;
import me.erano.com.repo.AuthorEmbeddedRepository;
import me.erano.com.repo.AuthorUuidRepository;
import me.erano.com.repo.BookNaturalRepository;
import me.erano.com.repo.BookRepository;
import me.erano.com.repo.BookUuidRepository;


@DataJpaTest
@ComponentScan(basePackages = {"me.erano.com.bootstrap "})

@ActiveProfiles("local")
//mysql dataları üzerine override etmek için yukardaki annatosyonu kullanıyoruz
//fakat bu hata vermesine neden olacaktır
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//o yüzden yukarda belirtilen annatosyonu kullandık
public class MySQLIntegrationTest {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	AuthorUuidRepository authorUuidRepository;
	
	@Autowired
	BookUuidRepository bookUuidRepository;
	
	@Autowired
	BookNaturalRepository bookNaturalRepository;
	 
	@Autowired
	AuthorCompositeRepository authorCompositeRepository;
	
	@Autowired
	AuthorEmbeddedRepository authorEmbeddedRepository;
	
	@Test
	void autorCompositeTest() {
		NameId nameId = new NameId("John", "T");
		AuthorEmbedded authorEmbedded= new AuthorEmbedded(nameId);
		
		AuthorEmbedded saved = authorEmbeddedRepository.save(authorEmbedded);
		assertThat(saved).isNotNull();
		
		AuthorEmbedded fetched = authorEmbeddedRepository.getById(nameId);
		
		assertThat(fetched).isNotNull();
		
	}

	@Test
	void testMySQL() {
		long countBefore = bookRepository.count();
		
		assertThat(countBefore).isEqualTo(2);
		//her çalıştığında 2 artacaktır ona göre yaz.
	}
	
	@Test
	void testAuthorUuid() {
		AuthorUuid authorUuid = authorUuidRepository.save(new AuthorUuid());
        assertThat(authorUuid).isNotNull();
        assertThat(authorUuid.getId()).isNotNull();

        AuthorUuid fetched = authorUuidRepository.getById(authorUuid.getId());
        assertThat(fetched).isNotNull();
	}
	@Test
    void testBookUuid() {
        BookUuid bookUuid = bookUuidRepository.save(new BookUuid());
        assertThat(bookUuid).isNotNull();
        assertThat(bookUuid.getId());

        BookUuid fetched = bookUuidRepository.getById(bookUuid.getId());
        assertThat(fetched).isNotNull();
    }
	
	@Test
    void bookNaturalTest() {
        BookNatural bookNatural = new BookNatural();
        bookNatural.setTitle("My Book");
        BookNatural saved = bookNaturalRepository.save(bookNatural);

        BookNatural fetched = bookNaturalRepository.getById(saved.getTitle());
        assertThat(fetched).isNotNull();
    }
	
	@Test
    void authorCompositeTest() {
        NameId nameId = new NameId("John", "T");
        AuthorComposite authorComposite = new AuthorComposite();
        authorComposite.setFirstName(nameId.getFirstName());
        authorComposite.setLastName(nameId.getLastName());
        authorComposite.setCountry("US");

        AuthorComposite saved = authorCompositeRepository.save(authorComposite);
        assertThat(saved).isNotNull();

        AuthorComposite fetched = authorCompositeRepository.getById(nameId);
        assertThat(fetched).isNotNull();
    }
}
