package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    private Integer id;

//    @Column(columnDefinition = "varchar(10) not null unique length(phoneNumber)=10")
    private String phoneNumber;






    @OneToOne
    @MapsId//only oneToOne relation its =    @PrimaryKeyJoinColumn
    @JsonIgnore // infinite loop so when get customers ignore customer
//    هذا تابع
    MyUser userCustomer;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    Set<Account> accounts;
}
