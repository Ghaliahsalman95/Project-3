package com.example.project3.Controller;

import com.example.project3.ApiResponse.APIResponse;
import com.example.project3.DTO.DTOEmployee;
import com.example.project3.Model.MyUser;
import com.example.project3.Service.EmpolyeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmpolyeeService empolyeeService;

    @GetMapping("/get-all-employees")
    public ResponseEntity getAllEmployees() {
        return ResponseEntity.status(200).body(empolyeeService.getAll());
    }

    @PostMapping("/register-employee")
    public ResponseEntity addemployee(@RequestBody @Valid DTOEmployee employee) {
        empolyeeService.add(employee);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Employee added successfully"));
    }
    @PutMapping("/update")
    public ResponseEntity updateEmpolyee(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid DTOEmployee dtoEmployee){
        empolyeeService.update(myUser.getUsername(), dtoEmployee);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Employee updated successfully"));
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity delete(@AuthenticationPrincipal MyUser myUser,@PathVariable String username){
        empolyeeService.delete(username);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Employee deleted successfully"));
    }
    @GetMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Log out successfully"));

    }
}
