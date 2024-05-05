package com.example.springsecuritypractice.model;

import com.example.springsecuritypractice.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private MyUserRepository repository;

    // This is the authentication part of the security configuration to save the user in the database
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = repository.findByUsername(username);

        if(user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .build();
        }else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

    }

    private String[] getRoles(MyUser userObj) {
        if(userObj.getRoles() == null) {
            return new String[]{"USER"};
        }
        return userObj.getRoles().split(",");
    }
}
