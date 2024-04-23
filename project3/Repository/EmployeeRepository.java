package com.example.project3.Repository;

import com.example.project3.Model.Employee;
import com.example.project3.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Employee findEmployeesById(Integer empolyeID);
    Employee findEmployeesByUserEmployee(MyUser user);
    @Query("select e from Employee e where e.userEmployee.username=?1")
    Employee findEmployeesByUsername(String username);
}
