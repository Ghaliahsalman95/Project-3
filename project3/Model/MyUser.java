package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Entity
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


   //    @Column(columnDefinition = "varchar(10) not null ")
    private String username;
//    @Column(columnDefinition = "varchar(10) not null")
    private String password;
   // @Column(columnDefinition = "varchar(20) length(name)<20 not null")
    private String name;
   // @Column(columnDefinition = "varchar(50) not null ")
    private String email;
    // @Column(columnDefinition = "varchar(10) not null check(role='CUSTOMER' or role='EMPLOYEE' or role='ADMIN') ")
    private String role;




    @OneToOne(cascade = CascadeType.ALL,mappedBy = "userEmployee")
    @JoinColumn(name = "user_id")
    @PrimaryKeyJoinColumn// هذا تابع
    private Employee userEmployee;


    //==================================



    @OneToOne(cascade = CascadeType.ALL,mappedBy = "userCustomer")
    @JoinColumn(name = "user_id")
    @PrimaryKeyJoinColumn//
    private Customer userCustomer;

    public MyUser(String username, String password, String name, String role,String email) {

        this.username=username;
        this.password=password;
        this.name=name;
        this.role=role;
        this.email=email;

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
