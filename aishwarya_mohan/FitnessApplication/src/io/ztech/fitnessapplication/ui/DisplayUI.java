package io.ztech.fitnessapplication.ui;

import java.util.HashMap;
import java.util.logging.Logger;

import io.ztech.fitnessapplication.DriverClass;
import io.ztech.fitnessapplication.beans.UserAccount;
import io.ztech.fitnessapplication.beans.UserAccountDetails;
import io.ztech.fitnessapplication.beans.UserPhysicalDetails;
import io.ztech.fitnessapplication.constants.DisplayStringConstants;
import io.ztech.fitnessapplication.service.AccountService;
import io.ztech.fitnessapplication.service.FoodService;
import io.ztech.fitnessapplication.service.UserPhysicalDetailsService;

public class DisplayUI {
	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());

	public void displayProfile(UserAccount account) {
		UserAccountDetails userProfile = new AccountService().getProfile(account);

		if (userProfile.getUserName() == null) {
			logger.info(DisplayStringConstants.ERROR);
		} else {
			logger.info(DisplayStringConstants.FIRST_NAME + userProfile.getFirstName());
			logger.info(DisplayStringConstants.LAST_NAME + userProfile.getLastName());
			logger.info(DisplayStringConstants.USER_NAME + userProfile.getUserName());
			logger.info(DisplayStringConstants.MAIL + userProfile.getEmail());
			logger.info(DisplayStringConstants.PHONE + userProfile.getPhone());
		}

	}

	public void displayphysicalProfile(UserAccount account) {
		UserPhysicalDetails physicalProfile = new UserPhysicalDetailsService().getphysicalProfile(account);

		if (physicalProfile == null) {
			logger.info(DisplayStringConstants.ERROR);
		} else {
			logger.info(DisplayStringConstants.USER_NAME + physicalProfile.getUserName());
			logger.info(DisplayStringConstants.HEIGHT + physicalProfile.getHeight());
			logger.info(DisplayStringConstants.WEIGHT + physicalProfile.getWeight());
			logger.info(DisplayStringConstants.AGE + physicalProfile.getAge());
			logger.info(DisplayStringConstants.GENDER + physicalProfile.getGender());
			logger.info(DisplayStringConstants.LIFESTYLE_MENU + physicalProfile.getActivty());
			logger.info(DisplayStringConstants.BMI + physicalProfile.getBmi());
			if (physicalProfile.getBmi() < 18.5) {
				logger.info(DisplayStringConstants.BMI_UNDER);
			} else if (physicalProfile.getBmi() < 25) {
				logger.info(DisplayStringConstants.BMI_NORM);
			} else {
				logger.info(DisplayStringConstants.BMI_OVER);
			}
			logger.info(DisplayStringConstants.BMR + physicalProfile.getBmr());

		}
	}

	public void displayFood() {
		HashMap<Integer, String> foodMap = new FoodService().displayFood();
		foodMap.forEach((K, V) -> logger.info(K + " " + V));
	}

}
