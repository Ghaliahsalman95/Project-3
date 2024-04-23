package com.example.project3.Repository;

import com.example.project3.Model.Customer;
import com.example.project3.Model.Employee;
import com.example.project3.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query("select e from Customer e where e.userCustomer.username=?1")
    Customer findCustomerByUserCustomer(String username);
    Customer findCustomerById(Integer id);
}
