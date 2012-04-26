package com.jike.mobile.gather.action;

import java.util.ArrayList;

import org.junit.Test;

import com.jike.mobile.gather.exception.ServiceException;
import com.jike.mobile.gather.model.AppUser;

public class TestAppUserAction {
	@Test
	public void testReadFromJson() throws ServiceException {
		ArrayList<AppUser> appUsers = new ArrayList<AppUser>();
		(new AppUserAction()).readFriendsFromJson("[{\"appId\":1,\"id\":\"x\",\"friends\":[\"aa\",\"bb\",\"cc\"]},{\"appId\":2,\"id\":\"y\",\"friends\":[\"ca\",\"cb\",\"cc\"]}]", appUsers);
	}
	
	
}
