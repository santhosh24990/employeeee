package com.glearning.employees.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.glearning.employees.model.Role;
import com.glearning.employees.model.User;
import com.glearning.employees.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BootstrapAppData {
	
	private  final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void insertEmployees(ApplicationReadyEvent event) {
		

		Role raviRole = new Role();
		raviRole.setRoleName("USER");
		
		Role santhoshRole = new Role();
		santhoshRole.setRoleName("ADMIN");
		
		Role managerRole = new Role();
		managerRole.setRoleName("MANAGER");
		
		User santhosh = new User();
		santhosh.setUsername("santhosh");
		santhosh.setPassword(this.passwordEncoder.encode("welcome"));
		santhosh.setEmailAddress("santhosh@gmail.com");
		
		santhoshRole.setUser(santhosh);
		santhosh.addRole(santhoshRole);
		
		User kishore = new User();
		kishore.setUsername("kishore");
		kishore.setPassword(this.passwordEncoder.encode("welcome"));
		kishore.setEmailAddress("kishore@gmail.com");
		
		managerRole.setUser(kishore);
		kishore.addRole(managerRole);

		User ravi = new User();
		ravi.setUsername("ravi");
		ravi.setPassword(this.passwordEncoder.encode("welcome"));
		ravi.setEmailAddress("ravi@gmail.com");
		
		raviRole.setUser(ravi);
		ravi.addRole(raviRole);
		
		this.userRepository.save(ravi);
		this.userRepository.save(santhosh);
		this.userRepository.save(kishore);
		
	}
}

