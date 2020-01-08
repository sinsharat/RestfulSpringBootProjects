package com.sharat.restfulwebservice.usermanage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManageService {

	@Autowired
	private UserManageDao userManageDao;

	public List<UserInfo> getAllUsers() {
		List<UserInfo> userInfoList = new ArrayList<UserInfo>();
		userManageDao.findAll().forEach(t -> userInfoList.add(t));
		return userInfoList;
	}

	public Optional<UserInfo> getUser(long userId) {
		return userManageDao.findById(userId);
	}

	public UserInfo addUserInfo(UserInfo userInfo) {
		UserInfo addedUserInfo = userManageDao.save(userInfo);
		return addedUserInfo;
	}

	public UserInfo updateuserInfo(UserInfo userInfo) {
		
		UserInfo queryUserInfo = getUser(userInfo.getUserId()).get();
		
		if (null == queryUserInfo)
		{
			return null;
		}
		
		UserInfo updatedUserInfo = userManageDao.save(userInfo);
		return updatedUserInfo;
	}
	
	public void deleteUserById(long userId) {
		userManageDao.deleteById(userId);
	}

}
