package springframework.IoC_Container001;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import springframework.IoC_Container001.services.PetStoreServiceImpl;

public class Application {
	
	private static ApplicationContext context;//Spring IoC container'i temsil eden arayüz
	
	public static void main(String[] args) {
		
		context= new ClassPathXmlApplicationContext("services.xml","daos.xml");
		//Bean'i oluşturması için container'a configuration metadatalarının olduğu xml dosyalarının path'ini veriyoruz
		
		PetStoreServiceImpl service = (PetStoreServiceImpl) context.getBean(PetStoreServiceImpl.class);
		
		System.out.println(service.getAccountDao().writeSomeThing("Account Dao"));
		System.out.println(service.getItemDao().writeSomeThing("Item Dao"));
		
	}

}
