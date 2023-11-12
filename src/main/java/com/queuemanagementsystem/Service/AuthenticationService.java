package com.queuemanagementsystem.Service;

import com.queuemanagementsystem.Pojo.UserInfo;
import com.queuemanagementsystem.Repository.AuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    AuthenticationRepo authRepo;
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        UserInfo userInfo=authRepo.findByEmail(userEmail);
        return new User(userInfo.getFirstName()+" "+userInfo.getLastName(),userInfo.getUserPassword(),new ArrayList<>());
    }
}
