package io.ztech.cricketapp.controller;

import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.constants.Regex;
import io.ztech.cricketapp.constants.UserMessages;
import io.ztech.cricketapp.delegate.UserDelegate;

public class UserController {
	UserDelegate userDelegate;
	Validator validator;

	public UserController() {
		userDelegate = new UserDelegate();
		validator = new Validator();
	}

	public boolean checkUser(User user) {
		return userDelegate.checkUser(user);
	}

	public User verifyUser(User user) {
		return userDelegate.verifyUser(user);
	}

	public boolean createUser(User newUser) {
		if (validator.validateInput(Regex.nameRegex, newUser.getFirstName(), UserMessages.INVALID_FIRST_NAME)
				&& validator.validateInput(Regex.nameRegex, newUser.getFirstName(), UserMessages.INVALID_FIRST_NAME)) {
			if (userDelegate.checkUser(newUser)) {
				System.out.println(UserMessages.USER_ALREADY_EXISTS);
				return false;
			}
			userDelegate.createUser(newUser);
			return true;
		} else {
			return false;
		}
	}
}
