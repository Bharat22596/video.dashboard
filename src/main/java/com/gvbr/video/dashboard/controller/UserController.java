package com.gvbr.video.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gvbr.video.dashboard.constants.APIReference;
import com.gvbr.video.dashboard.constants.Constants;
import com.gvbr.video.dashboard.models.Metadata;
import com.gvbr.video.dashboard.models.Response;
import com.gvbr.video.dashboard.models.User;
import com.gvbr.video.dashboard.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@Api(value = Constants.USER_CONTROLLER)
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(value = APIReference.CREATE_USER, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Create new user")
	public @ResponseBody Response<User> createUser(@RequestBody User user) {

		user = userService.createUser(user);

		return new Response<>(new Metadata(true, "New user created with user name:" + user.getUserName()), user);

	}

	@GetMapping(value = APIReference.GET_USER, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get existing user")
	public @ResponseBody Response<User> getUser(@RequestParam("userId") Long userId) {

		User user = userService.getUser(userId);

		return new Response<>(
				new Metadata(true,
						"Fetched existing user with user name:" + user.getUserName() + " and user id :" + userId),
				user);

	}
	
	@GetMapping(value = APIReference.AUTHENTICATE_USER, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get existing user")
	public @ResponseBody Response<Long> getUserAuth(@RequestParam("username") String userName, @RequestParam("password") String password) {

		Long userId = userService.getUserAuth(userName,password);

		return new Response<>(
				new Metadata(true,
						"user Authenticated"),
				userId);

	}

	@PutMapping(value = APIReference.UPDATE_USER, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update Existing user")
	public @ResponseBody Response<User> updateUser(@RequestBody User user, @RequestParam("userId") Long userId) {

		user = userService.updateUser(user, userId);

		return new Response<>(new Metadata(true,
				"Updated e xisting with user id:" + userId + " and user name: " + user.getUserName()), user);

	}

}
