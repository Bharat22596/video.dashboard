package com.gvbr.video.dashboard.service.impl;

import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gvbr.video.dashboard.models.User;
import com.gvbr.video.dashboard.repository.UserRepository;
import com.gvbr.video.dashboard.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Override
	public User createUser(User user) {

		if (!StringUtils.hasText(user.getEmailId()) && EmailValidator.getInstance().isValid(user.getEmailId())) {
			throw new RuntimeException("Bad Request. Email Id is missing or invalid");
		} else if (!StringUtils.hasText(user.getUserName())) {
			throw new RuntimeException("Bad Request. User Name is missing");
		} else if (!StringUtils.hasText(user.getPassword())) {
			throw new RuntimeException("Bad Request. Password is missing");
		}
		return userRepo.save(user);
	}

	@Override
	public User updateUser(User user, Long userId) {

		User existingUser = userRepo.findById(userId).orElse(new User());
		if (StringUtils.hasText(user.getEmailId()))
			existingUser.setEmailId(user.getEmailId());
		if (StringUtils.hasText(user.getUserName()))
			existingUser.setUserName(user.getUserName());
		if (StringUtils.hasText(user.getPassword()))
			existingUser.setEmailId(user.getPassword());
		existingUser.setUpdatedBy(user.getUserName());

		if (!StringUtils.hasText(existingUser.getEmailId())
				&& EmailValidator.getInstance().isValid(existingUser.getEmailId())) {
			throw new RuntimeException("Bad Request. Email Id is missing or invalid");
		} else if (!StringUtils.hasText(existingUser.getUserName())) {
			throw new RuntimeException("Bad Request. User Name is missing");
		} else if (!StringUtils.hasText(existingUser.getPassword())) {
			throw new RuntimeException("Bad Request. Password is missing");
		}
		return userRepo.save(existingUser);
	}

	@Override
	public User getUser(Long userId) {
		if (userId == null) {
			throw new RuntimeException("Bad Request. User Name is missing");
		}
		return userRepo.findById(userId).orElse(null);
	}

	@Override
	public Long getUserAuth(String userName, String password) {
		log.info("Authentication of username:{} and password:{}",userName,password);
		if (!StringUtils.hasText(userName)) {
			throw new RuntimeException("Bad Request. User Name is missing");
		} else if (!StringUtils.hasText(password)) {
			throw new RuntimeException("Bad Request. Password is missing");
		}
		
		List<User> users = userRepo.findAllByUserNameAndPassword(userName, password);
		
		
		
		if(users!=null && users.size()>0) {
			log.info("User found.");
			return users.get(0).getUserId();
		}

		log.info("User missing");
		return null;
	}

}
