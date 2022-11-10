package com.glearning.employees.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.glearning.employees.model.DomainUserDetails;
import com.glearning.employees.model.User;
import com.glearning.employees.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Invalid User"));
        System.out.println("User from the repository ");
        System.out.println(user);
        //convert into UserDetails object which Spring Security can understand
        return new DomainUserDetails(user);
    }
}