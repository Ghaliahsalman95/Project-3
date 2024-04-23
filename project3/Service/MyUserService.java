package com.example.project3.Service;

import com.example.project3.ApiResponse.APIException;
import com.example.project3.Model.Employee;
import com.example.project3.Model.MyUser;
import com.example.project3.Repository.MyUserRepoistry;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserService {

    private final MyUserRepoistry myUserRepoistry;

    public List<MyUser> getAll() {
        if (myUserRepoistry.findAll().isEmpty())
            throw new APIException("Empty list");
        else return myUserRepoistry.findAll();
    }

    public void addUser(MyUser myUser) {
        myUser.setPassword(new BCryptPasswordEncoder().encode(myUser.getPassword()));
        myUserRepoistry.save(myUser);
    }

    public void update(String username, MyUser myUser) {
        MyUser myUser1 = myUserRepoistry.findUserByUsername(username);
        if (myUser1 == null) {
            throw new APIException("User Not found");
        }
        myUser1.setPassword(new BCryptPasswordEncoder().encode(myUser.getPassword()));
        myUser1.setEmail(myUser.getEmail());
        myUser1.setRole(myUser.getRole());
        myUser1.setName(myUser.getName());
        myUser1.setUsername(myUser.getUsername());
        myUserRepoistry.save(myUser1);

    }

    public void delete(String username) {
        MyUser user = myUserRepoistry.findUserByUsername(username);

        if (user == null) {
            throw new APIException("Employee Not found");
        }
        myUserRepoistry.delete(user);


    }
}