package ir.ac.sbu.ie.project2.Phase2.service;

import ir.ac.sbu.ie.project2.Phase2.model.User;

public interface UserService {
	void saveUser(User user);
	boolean isUserAlreadyPresent(User user);
	User findUserByEmail(String email);
}
