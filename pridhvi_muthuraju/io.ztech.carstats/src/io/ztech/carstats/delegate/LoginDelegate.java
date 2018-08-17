package io.ztech.carstats.delegate;

import io.ztech.carstats.beans.User;
import io.ztech.carstats.dao.LoginDAO;

public class LoginDelegate {
	LoginDAO loginDAO = new LoginDAO();

	public boolean logoutLogin(Boolean flag,User user) {
		return loginDAO.logoutLogin(flag,user);
	}

	public boolean signup(User user) {
		return loginDAO.signup(user) && Validator.validateUsername(user.getUserName())
				&& Validator.validatePassword(user.getPassword());
	}
}
