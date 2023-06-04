package me.erano.com;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import me.erano.com.domain.Book;
import me.erano.com.repo.BookRepository;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest

@ComponentScan(basePackages = {"me.erano.com.bootstrap "}) 
//bu annatasyon bootstrap yaptığımız sınıftaki nesneleride test aşamasında sayıyor.
//assertThat ile kullandıgımız countlar hata sebebi çıkartabilir
public class SpringbootJpaTestSlice {

	@Autowired
	BookRepository bookRepository;
	
	//@Rollback(value=false)
	@Commit
	@Order(1)
	@Test
	void testJpaTestSlice() {
		long countBefore = bookRepository.count();
		assertThat(countBefore).isEqualTo(2);
		
		bookRepository.save(new Book("My Book","123414134","Self",null));
		
		long countAfter = bookRepository.count();
		
		assertThat(countBefore).isLessThan(countAfter);
	}
	@Order(2)
	@Test
	void testJpaTestSliceTransaction() {
		long countBefore = bookRepository.count();
		
		assertThat(countBefore).isEqualTo(3);
	}

}
