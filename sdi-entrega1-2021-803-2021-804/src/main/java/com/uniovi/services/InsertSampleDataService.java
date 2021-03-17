package com.uniovi.services;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
@Service
public class InsertSampleDataService {
	
	@Autowired
	private UserService usersService;
	
	@Autowired
	private RolesService rolesService;
	
	@PostConstruct
	public void init() {
		User user1 = new User("UO101010@uniovi.es", "Pedro", "Calvo");
		user1.setPassword("123456");
		user1.setRol(rolesService.getRoles()[0]);
		User user2 = new User("UO101011@uniovi.es", "Rodrígo", "González");
		user2.setPassword("123456");
		user2.setRol(rolesService.getRoles()[0]);
		User user3 = new User("UO101012@uniovi.es", "Sara", "Fernández");
		user3.setPassword("123456");
		user3.setRol(rolesService.getRoles()[0]);
		User user4 = new User("UO101013@uniovi.es", "Lara", "Peña");
		user4.setPassword("123456");
		user4.setRol(rolesService.getRoles()[0]);
		User user5 = new User("UO101014@uniovi.es", "Pelayo", "Martínez");
		user5.setPassword("123456");
		user5.setRol(rolesService.getRoles()[0]);
		User user6 = new User("admin@email.com", "Javier", "López");
		user6.setPassword("admin");
		user6.setRol(rolesService.getRoles()[1]);
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
	}
}
