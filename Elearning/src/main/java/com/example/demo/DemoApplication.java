package com.example.demo;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.ParseException;

@SpringBootApplication
public class DemoApplication {


    public static void main(String[] args) throws ParseException {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        ApplicationContext ctx=SpringApplication.run(DemoApplication.class, args);


       /* RoleRepository roleRepository=ctx.getBean(RoleRepository.class);
        roleRepository.save(new Role("ADMIN", "administrateur"));
        roleRepository.save(new Role("PROF", "professeur"));
        roleRepository.save(new Role("ETUDIANT", "etudiant"));
*/

   /*     UserRepository userRepository=ctx.getBean(UserRepository.class );
        userRepository.save(new User("admin@gmail.com", encoder.encode("admin"),"ab", "kh","F",true));

*/

    }

}
