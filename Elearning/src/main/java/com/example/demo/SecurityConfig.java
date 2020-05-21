package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    public void globalConfig(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception{
        /*auth.inMemoryAuthentication().withUser("admin").password("{noop}123").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("etu").password("{noop}123").roles("ETUDIANT");*/
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        auth.jdbcAuthentication()
                .usersByUsernameQuery("select email as principal, password as credentials, true from users where email=?")
                .authoritiesByUsernameQuery("select users.email as principal, role.role  as role from users INNER JOIN role on (users.role= role.id) WHERE users.email =?")
                .rolePrefix("ROLE_").dataSource(dataSource).passwordEncoder(encoder);


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/css/**","/js/**","/images/**","/webjars/**").permitAll()
                    /***Users***
                    .antMatchers("/users").access("hasRole('ROLE_ADMIN')")
                    .antMatchers("/findUser/{id}").access("hasRole('ROLE_ADMIN')")
                    .antMatchers("/users/{id}/delete").access("hasRole('ROLE_ADMIN')")
                    .antMatchers("/create").access("hasRole('ROLE_ADMIN')")
*/
                .antMatchers("/users").hasAuthority("ROLE_ADMIN")

                /***Cours***
                    .antMatchers("/cours/save").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROF')")
                    .antMatchers("/editcours/{coursId}").access("hasRole('ROLE_ADMIN')")
                    .antMatchers("/deletecours/{coursId}").access("hasRole('ROLE_ADMIN')")
*/





                .anyRequest()
                    .authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")

                    .permitAll()
                     .defaultSuccessUrl("/")

                .and()
                .logout().    //logout configuration
                     logoutUrl("/Logout").
                        logoutSuccessUrl("/login");




    }
}
