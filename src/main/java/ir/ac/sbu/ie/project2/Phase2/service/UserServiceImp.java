package ir.ac.sbu.ie.project2.Phase2.service;

import ir.ac.sbu.ie.project2.Phase2.model.Role;
import ir.ac.sbu.ie.project2.Phase2.model.User;
import ir.ac.sbu.ie.project2.Phase2.repository.RoleRepository;
import ir.ac.sbu.ie.project2.Phase2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
    RoleRepository roleRepository;
	@Autowired
    UserRepository userRepository;

	@Override
	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERIFIED");
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		return userRepository.findUserByEmail(user.getEmail()) != null;
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	@Override
	public void updateUserByEmail(String email, String name, String lastName, String password) {
		userRepository.updateUserByEmail(email, name, lastName, encoder.encode(password));
	}

}
