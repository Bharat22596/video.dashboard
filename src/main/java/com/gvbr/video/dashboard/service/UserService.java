package com.gvbr.video.dashboard.service;

import com.gvbr.video.dashboard.models.User;

public interface UserService {

	public User createUser(User user);
	
	public User updateUser(User user,Long userId);
	
	public User getUser(Long userId);

	public Long getUserAuth(String userName, String password);

}
