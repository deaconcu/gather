package com.jike.mobile.gather.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.jike.mobile.gather.exception.ServiceException;
import com.jike.mobile.gather.model.App;
import com.jike.mobile.gather.model.AppUser;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"../../../../../../applicationContext.xml"})
@TransactionConfiguration(defaultRollback = false)
public class TestAppUserDaoImpl extends AbstractTransactionalJUnit4SpringContextTests {
	@Test
	public void testUpdate() throws ServiceException {
//		AppUser appUser = new AppUser();
//		App app = new App(5);
//		
//		appUser.setUserId("test");
//		appUser.setApp(app);
//		
//		appUser.setCreateTime(System.currentTimeMillis());
//		
//		(new AppUserDaoImpl()).save(appUser);
	}
}
