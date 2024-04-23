package com.example.project3.SecurityConfig;

import com.example.project3.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    //authenticated
 //MyUserDetailsService myUserDetialsService;
 private final MyUserDetailsService myUserDetialsService;
@Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetialsService);
       daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());//never save password without decrypt

       return daoAuthenticationProvider;
        //
    }

@Bean//Authorization
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{

//        httpSecurity.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .and()//delete/{admin}/{username}   /get-all/{admin}
//                .authenticationProvider(daoAuthenticationProvider()).authorizeHttpRequests().
//                requestMatchers("/api/v1/auth/register").permitAll()
//                .requestMatchers("/api/v1/auth/delete/","/get-all/{admin}").hasAuthority("ADMIN")
//                .anyRequest().authenticated()
//                .and().logout().logoutUrl("api/v1/auth/logout").deleteCookies("JSESSIONID").invalidateHttpSession(true).and().
//                httpBasic();
//---------------------------


    httpSecurity.csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
            .authorizeRequests()
            .requestMatchers("/api/v1/auth/register-employee").permitAll()
            .requestMatchers("/api/v1/employee/register-employee").permitAll()
            .requestMatchers("/api/v1/employee/update").hasAuthority("EMPLOYEE")
            .requestMatchers("api/v1/employee/delete/{username}").hasAuthority("ADMIN")
            .requestMatchers("/api/v1/employee/get-all-employees").hasAuthority("ADMIN")
            .requestMatchers("/api/v1/employee/get-all-users").hasAuthority("ADMIN")
            .requestMatchers("api/v1/customer/get-all-customers").hasAuthority("ADMIN")
            .requestMatchers("/api/v1/employee/delete/{username}").hasAuthority("ADMIN")
            .requestMatchers("api/v1/auth/update").hasAuthority("ADMIN")
            .requestMatchers("api/v1/auth/delete/{username}").hasAuthority("ADMIN")
            .requestMatchers("api/v1/auth/delete/{username}").hasAuthority("ADMIN")
            .requestMatchers("api/v1/account/block-account/{accountId}").hasAuthority("ADMIN")
            .requestMatchers("api/v1/auth/get-all-users").hasAuthority("ADMIN")
            .requestMatchers("api/v1/auth/add-user").permitAll()
            .requestMatchers("api/v1/customer/register-customer").permitAll()
            .requestMatchers("api/v1/account/add").hasAuthority("CUSTOMER")
            .requestMatchers("api/v1/customer/delete").hasAuthority("CUSTOMER")
            .requestMatchers("api/v1/account/view-account-details/{accountId}").hasAuthority("CUSTOMER")
            .requestMatchers("api/v1/account/active-bank-account/{accountId}").hasAuthority("CUSTOMER")
            .requestMatchers("api/v1/account/deposit-account/{accountId}/{amount}").hasAuthority("CUSTOMER")
            .requestMatchers("api/v1/account/withdraw-account/{accountId}/{amount}").hasAuthority("CUSTOMER")
            .requestMatchers("api/v1/account/transfer-funds-between-accounts/{myaccountId}/{accountId}/{amount}").hasAuthority("CUSTOMER")

            .anyRequest().authenticated()
            .and()
            .logout()
            .logoutUrl("/api/v1/auth/logout")
            .logoutUrl("api/v1/customer/logout")
            .logoutUrl("api/v1/employee/logout")
            .deleteCookies("JSESSIONID")
            .invalidateHttpSession(true)
            .and()
            .httpBasic();
//---------------------
        // .requestMatchers("/api/v1/auth/**").permitAll();////
        // .requestMatchers("/api/v1/auth/**").permitAll();//
        // .requestMatchers("/api/v1/auth/**").permitAll();//
        // .requestMatchers("/api/v1/auth/**").permitAll();//
//can do requestMatchers 60 lines cover alls apis in every controllers classes


        return httpSecurity.build(); }

//** all API (api/v1/auth) in this page have same authorize or one by one between /
// or HttpMethod.GET

    //1- config DaoAuthenticationProvider
    //2- implement myUserDetialsService
    //
}
