package springframework.IoC_Container001.services;

import springframework.IoC_Container001.dao.AccountDao;
import springframework.IoC_Container001.dao.ItemDao;

public class PetStoreServiceImpl {

	private AccountDao accountDao;
	
	private ItemDao itemDao;
	
	public PetStoreServiceImpl() {
		
	}

	public PetStoreServiceImpl(AccountDao accountDao, ItemDao itemDao) {
		this.accountDao = accountDao;
		this.itemDao = itemDao;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	
	
}
