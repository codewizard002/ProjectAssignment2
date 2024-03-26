package com.assignment.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.assignment.entity.User;
import com.assignment.repository.UserRepo;
import com.assignment.service.UserService;



@Controller
@RequestMapping("/admin")
public class AdminController {

	private final RestTemplate restTemplate;
	
	 @Autowired
	    public AdminController(RestTemplate restTemplate) {
	        this.restTemplate = restTemplate;
	    }

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserService userService;

	@ModelAttribute
	public void commonUser(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			User user = userRepo.findByEmail(email);
			m.addAttribute("user", user);
		
		}
	}
	
	@GetMapping("/hello")
	public String sayHello() {
		return "we are using docker";
    }

	@GetMapping("/profile")
	//@QueryMapping("/profile")
	public String profile(Model m) {
		List<User> user = userRepo.findAll();
        m.addAttribute("user", user);
		return "admin_profile";
      
	}
	 @GetMapping("/selectUser")
	    public String selectUser(Model model) {
	        List<User> userList = userService.getAllUsers();
	        model.addAttribute("users", userList);
	        return "selectUser";
	    }
	 
	 @PostMapping("/save")
	    public String saveUser(@ModelAttribute("user") User user) {
	        userService.save(user);
	        return "redirect:/";
	    }
	 @PostMapping("/saveDataEndpoint")
	    public String saveData(@RequestBody User user) {
	        // Process the received data (e.g., save to database)
		 userService.saveUser(user);
	        System.out.println("Received data: " + user);
	        return "Data saved successfully!";
	    }
	 
	    @GetMapping("/userDetails")
	    public String userDetails(@RequestParam("id") int id, Model model) {
	        User user = userService.getUserById(id);
	        model.addAttribute("user", user);
	        return "userDetails";
	    }
	    @GetMapping("/showFormForUpdate/{id}")
	    public String updateForm(@PathVariable(value = "id") int id, Model model) {
	        User user = userService.getUserById(id);
	        model.addAttribute("user", user);
	        return "update";
	    }
	 
		/*
		 * @GetMapping("/deleteEmployee/{id}") public String
		 * deleteThroughId(@PathVariable(value = "id") long id) {
		 * userService.deleteViaId(id); return "redirect:/";
		 
	    }**/
	    
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }
	
//	public String getRemoteData() {
//        String url = "http://user-service/user/profile"; 
//        return restTemplate.getForObject(url, String.class);
//    }
	
	//this is for feignclient
	/*
	 * @GetMapping("/user/{id}") private ResponseEntity<User>
	 * getDetails(@PathVariable("id") int id) { User user =
	 * userService.getUserById(id); return
	 * ResponseEntity.status(HttpStatus.OK).body(user); }
	 */
	
	
	/*
	 * @GetMapping("/users") public String getusers(Model m) { List<User> users =
	 * userRepo.findAll(); m.addAttribute("users", users); return "users"; }
	 */
	
	 @PostMapping("/getUserInfo")
	    @ResponseBody
	    public User getUserInfo(@RequestParam("id") int id) {
	        // Fetch user details from the database based on userId
	        return userService.getUserById(id);
	    }
}