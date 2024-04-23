package com.example.project3.Service;

import com.example.project3.ApiResponse.APIException;
import com.example.project3.Model.MyUser;
import com.example.project3.Repository.MyUserRepoistry;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
    private final MyUserRepoistry myUserRepoistry;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = myUserRepoistry.findUserByUsername(username);
        if (user == null) throw new APIException("Wrong username or password");
        return user;
    }






}