package com.example.project3.Controller;

import com.example.project3.ApiResponse.APIResponse;
import com.example.project3.DTO.DTOCustomer;
import com.example.project3.DTO.DTOEmployee;
import com.example.project3.Model.Employee;
import com.example.project3.Model.MyUser;
import com.example.project3.Service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.DTD;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class MyUserController {

    private final MyUserService myUserService;
    private final EmpolyeeService empolyeeService;
    private final CustomerService customerService;
private final AccountService accountService;

    @GetMapping("/get-all-accounts")
    public ResponseEntity getAllAccount() {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAll());
    }
    @GetMapping("/get-all-users")
    public ResponseEntity getAll() {
        return ResponseEntity.status(200).body(myUserService.getAll());
    }
    //


    @PostMapping("/add-user")
    public ResponseEntity addUser(@RequestBody @Valid MyUser myUser){
        myUserService.addUser(myUser);
        return ResponseEntity.status(200).body(new APIResponse("User Added successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid MyUser newUser){
        myUserService.update(myUser.getUsername(), newUser);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("User updated successfully"));
    }
    @DeleteMapping("/delete/{username}")
    public ResponseEntity delete(@AuthenticationPrincipal MyUser myUser, @PathVariable String username){
        myUserService.delete(username);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("User deleted successfully"));
    }
}
