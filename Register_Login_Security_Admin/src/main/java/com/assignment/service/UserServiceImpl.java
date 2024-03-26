package com.assignment.service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.assignment.entity.User;
import com.assignment.repository.UserRepo;

import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;

	//private final GraphQLWebClient graphQLClient;

	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Override
	public User saveUser(User user) {

		String password=passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		user.setRole("ROLE_ADMIN");
		User newuser = userRepo.save(user);

		return newuser;
	}

	@Override
	public void removeSessionMessage() {

		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg");
	}
	
	  public User getUserById(int id) {
	        Optional<User> userOptional = userRepo.findById(id);
	        return userOptional.orElse(null);
	    }
	  
	  public List<User> getAllUsers() {
	        return userRepo.findAll();
	    }

	  @Override 
	  public void save(User user)
	    {
		  userRepo.save(user);
	    }
	 
	   
	/*
	 * @Override public User getUserById(int id) { Optional<User> userOptional =
	 * userRepo.findById(id); return userOptional.orElse(null); }
	 */
	/*
	 * //to use openfeign and to communicate with other services
	 * 
	 * @Autowired private ModelMapper mapper;
	 * 
	 * public User getUserById(int id) { Optional<User> user =
	 * userRepo.findById(id); User u= mapper.map(user, User.class); return u; }
	 */
	 

	      
	// Edit user fields based on input for graphql
	
	
	/*public User editUser(String id, EditUserInput input) {
        // Fetch user from the repository
        User user = userRepository.findById(id);
	 * if (input.getName() != null) { user.setName(input.getName()); }
	 * 
	 * user.setName(input.getName()); }
	 * 
	 * user.setName( if (input.getEmail() != null) {
	 * user.setEmail(input.getEmail()); }
	 * 
	 * user.setEmail(input.getEmail());
	 * 
	 * // Update other fields as needed
	 * 
	 * // Save the updated user
	 * 
	 * 
	 * return userRepository.save(user);
	 */
	    }

