package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


//• id: Generated automatically.
//• accountNumber:
//- Cannot be null.
//- Must follow a specific format (e.g., "XXXX-XXXX-XXXX-XXXX").
//• balance:
//- Cannot be null.
//- Must be a non-negative decimal number.
//• isActive:
//- By default, false.

//    @NotNull
//    @Pattern(regexp = "\\d{4}-\\d{4}-\\d{4}-\\d{4}", message = "Invalid account number format. Should be XXXX-XXXX-XXXX-XXXX")
//    //@Column(nullable = false, unique = true) // Define column-specific properties

    private String accountNumber;

    //@PositiveOrZero(message = "Balance must be a non-negative decimal number.")
    //@Column(columnDefinition = "int     balance int default 0 ")
    private double balance;
   // @AssertFalse
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "user_customer_id")
    @JsonIgnore
    Customer customer;

}
