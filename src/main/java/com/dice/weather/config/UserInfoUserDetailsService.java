package com.dice.weather.config;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws BadCredentialsException{
       if(username.equals("Rahul")){
           return new User("Rahul","rahul@123",new ArrayList<>());
       }
       else if(username.equals("Rohit")) {
            return new User("Rohit", "rohit@123", new ArrayList<>());
        }
       else {
           throw new BadCredentialsException("Invalid Credentials!!");

       }

    }
}
