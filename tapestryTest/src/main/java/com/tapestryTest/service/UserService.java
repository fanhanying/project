package com.tapestryTest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tapestryTest.domain.User;
import com.tapestryTest.repository.UserRepository;

/** 
 * @author hanying.fan
 * @date 2017年7月24日 下午3:27:51 
 */
@Service
public class UserService {
	
	@Autowired
	public UserRepository userRepository;
	
	public User findById(String id) {
		return userRepository.findById(id);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public void save(User user) {
		userRepository.save(user);
	}

}
