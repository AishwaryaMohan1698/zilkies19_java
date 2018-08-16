package io.ztech.carstats.services;

import io.ztech.carstats.beans.Rating;
import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.User;
import io.ztech.carstats.delegate.AddRatingDelegate;

public class AddRatingService {
	AddRatingDelegate addRatingDelegate = new AddRatingDelegate();

	public boolean addRating(Specification specification, Rating rating, User user) {
		return addRatingDelegate.addRating(specification, rating, user);
	}
}
