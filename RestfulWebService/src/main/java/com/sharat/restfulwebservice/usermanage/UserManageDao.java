package com.sharat.restfulwebservice.usermanage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserManageDao extends CrudRepository<UserInfo, Long> {
	
}
