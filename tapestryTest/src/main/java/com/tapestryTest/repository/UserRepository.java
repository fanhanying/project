package com.tapestryTest.repository;

import org.springframework.stereotype.Repository;

import com.tapestryTest.domain.User;
import com.tapestryTest.repository.base.GenericRepository;

/** 
 * @author hanying.fan
 * @date 2017年7月24日 下午3:26:42 
 */
@Repository
public interface UserRepository extends GenericRepository<User>{
	
	User findById(String id);

}
