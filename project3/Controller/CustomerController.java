package com.example.project3.Controller;
import com.example.project3.ApiResponse.APIResponse;
import com.example.project3.DTO.DTOCustomer;
import com.example.project3.DTO.DTOEmployee;
import com.example.project3.Model.MyUser;
import com.example.project3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

private final CustomerService customerService;
   // "username":"test","phoneNumber":"phoneNumber","password":"123","name":"test","email":"email"

    @GetMapping("/get-all-customers")
    public ResponseEntity getAllCustomers() {
        return ResponseEntity.status(200).body(customerService.getAll());
    }

    @PostMapping("/register-customer")
    public ResponseEntity addcustomer(@RequestBody @Valid DTOCustomer customer) {
        customerService.add(customer);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Customer added successfully"));
    }
    @PutMapping("/update")
    public ResponseEntity updateEmpolyee(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid DTOCustomer dtoCustomer){
        customerService.update(myUser.getUsername(), dtoCustomer);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Employee added successfully"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@AuthenticationPrincipal MyUser myUser){
        customerService.delete(myUser.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Customer deleted successfully"));
    }
    @GetMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Log out successfully"));

    }
}
