package com.assignment.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.assignment.entity.User;


public interface UserRepo extends JpaRepository<User, Integer> {

	public User findByEmail(String emaill);
	
	/*
	 * @Query( nativeQuery = true, value =
	 * "SELECT ea.id, ea.name, ea.password FROM newsecurity_db.user ea join security_db.user e on e.id = ea.id where ea.id=:id"
	 * ) Optional<User> findAddressByEmployeeId(@Param("id") int employeeId); }
	 */
	
}
