package com.example.project3.Service;

import com.example.project3.ApiResponse.APIException;
import com.example.project3.DTO.DTOEmployee;
import com.example.project3.Model.Customer;
import com.example.project3.Model.Employee;
import com.example.project3.Model.MyUser;
import com.example.project3.Repository.EmployeeRepository;
import com.example.project3.Repository.MyUserRepoistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpolyeeService {

    private final EmployeeRepository employeeRepository;
    private final MyUserRepoistry myUserRepoistry;
    @Autowired
    private final MyUserService myUserService;

    public List<Employee> getAll() {
        if (employeeRepository.findAll().isEmpty()) throw new APIException("EmptyList");
        else return employeeRepository.findAll();
    }

    public void add(DTOEmployee employee) {
        MyUser myUser = new MyUser(employee.getUsername(), new BCryptPasswordEncoder().encode(employee.getPassword()), employee.getName(), "EMPLOYEE", employee.getEmail());
//
        myUserService.addUser(myUser);
//-------------------------------------------------------
        Employee retrive = new Employee(null, employee.getPosition(), employee.getSalary(), myUserRepoistry.findUserByUsername(employee.getUsername()));
        employeeRepository.save(retrive);
        //------------------------------------------------------------------
    }

    //    public MyUser(String username, String password, String name, String role,String email) {
    public void update(String username, DTOEmployee dtoEmployee) {
        MyUser user = myUserRepoistry.findUserByUsername(username);
        if (user == null) {
            throw new APIException("Employee Not found");
        }
        MyUser myUser = new MyUser(dtoEmployee.getUsername(), dtoEmployee.getPassword(), dtoEmployee.getName(), "EMPLOYEE", dtoEmployee.getEmail());
        Employee employee = employeeRepository.findEmployeesByUsername(username);
        employee.setPosition(dtoEmployee.getPosition());
        employee.setSalary(dtoEmployee.getSalary());
        myUserService.update(username, myUser);
        employeeRepository.save(employee);


    }

    public void delete(String username) {
        MyUser user = myUserRepoistry.findUserByUsername(username);
        Employee employee = employeeRepository.findEmployeesByUsername(username);

        if (user == null) {
            throw new APIException("Employee Not found");
        }
        myUserRepoistry.delete(user);
        employeeRepository.delete(employee);


    }
}

//        employee.getId().setRole("EMPLOYEE");
//        employee.setId(employee.getUserEmployee().getId());
