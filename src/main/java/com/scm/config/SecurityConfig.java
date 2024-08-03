package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.services.impl.SecurityCustomUserDetailService;


@Configuration
public class SecurityConfig {

    // user create and login using java code with in memory service
    // @Bean
    // public UserDetailsService userDetailsService(){

    //     UserDetails user =  User
    //     .withDefaultPasswordEncoder()
    //     .username("admin123")
    //     .password("admin123")
    //     .roles("ADMIN", "USER")
    //     .build();

    //     UserDetails user2 = 
    //     User
    //     .withUsername("user123")
    //     .password("user123")
    //     .build();

    //     return new InMemoryUserDetailsManager(user,user2);
    // }

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    // configuration of authentication provider
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        // UserDetails service object
        daoAuthenticationProvider.setUserDetailsService(userDetailService);

        // password encoder object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        // configuration

        // configure urls public or private
        httpSecurity.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home","/register","/services").permitAll();
            authorize.requestMatchers("user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        // form default login
        // if you have to change anything regarding form login change here
        httpSecurity.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    } 

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
