package com.example.project3.Service;

import com.example.project3.ApiResponse.APIException;
import com.example.project3.DTO.DTOCustomer;
import com.example.project3.DTO.DTOEmployee;
import com.example.project3.Model.Customer;
import com.example.project3.Model.Employee;
import com.example.project3.Model.MyUser;
import com.example.project3.Repository.CustomerRepository;
import com.example.project3.Repository.EmployeeRepository;
import com.example.project3.Repository.MyUserRepoistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final MyUserRepoistry myUserRepoistry;
    @Autowired
    private final MyUserService myUserService;

    public List<Customer> getAll(){
        if (customerRepository.findAll().isEmpty())throw new APIException("EmptyList");
        else return customerRepository.findAll();
    }
    public void add( DTOCustomer customer){
        MyUser myUser=new MyUser(customer.getUsername(),new BCryptPasswordEncoder().encode(customer.getPassword()),customer.getName(),"CUSTOMER",customer.getEmail());

        myUserService.addUser(myUser);
//-------------------------------------------------------
        Customer retrive= new Customer(null, customer.getPhoneNumber(), myUserRepoistry.findUserByUsername(customer.getUsername()),null);
        customerRepository.save(retrive);
        //------------------------------------------------------------------
    }



    public void update(String username, DTOCustomer customer) {
        MyUser user = myUserRepoistry.findUserByUsername(username);
        if (user == null) {
            throw new APIException("Customer Not found");
        }
        MyUser myUser = new MyUser(customer.getUsername(), customer.getPassword(), customer.getName(), "CUSTOMER", customer.getEmail());
        Customer customer1 = customerRepository.findCustomerByUserCustomer(username);
        customer1.setPhoneNumber(customer.getPhoneNumber());
        myUserService.update(username, myUser);
        customerRepository.save(customer1);
    }
public void delete(String username){
    MyUser user = myUserRepoistry.findUserByUsername(username);
    Customer customer=customerRepository.findCustomerByUserCustomer(username);

    if (user == null) {
        throw new APIException("Customer Not found");
    }
    myUserRepoistry.delete(user);
    customerRepository.delete(customer);



}



}
