package com.example.demo;

import com.example.demo.entities.Filiere;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.FiliereRepository;
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
/*

        RoleRepository roleRepository=ctx.getBean(RoleRepository.class);
        roleRepository.save(new Role("ADMIN", "administrateur"));
        roleRepository.save(new Role("PROF", "professeur"));
        roleRepository.save(new Role("ETUDIANT", "etudiant"));


        UserRepository userRepository=ctx.getBean(UserRepository.class );
        userRepository.save(new User("admin@gmail.com", encoder.encode("admin123456789"),"ab", "kh","F",true));


        FiliereRepository filiereRepository = ctx.getBean(FiliereRepository.class);
        filiereRepository.save(new Filiere("GI1","Génie Informatique 1"));
        filiereRepository.save(new Filiere("GI2","Génie Informatique 2"));
        filiereRepository.save(new Filiere("ER1","Energies Renouvelables 1"));
        filiereRepository.save(new Filiere("ER2","Energies Renouvelables 2"));
        filiereRepository.save(new Filiere("TM1","Techniques de Management 1"));
        filiereRepository.save(new Filiere("TM2","Techniques de Management 2"));
        filiereRepository.save(new Filiere("GODT1","Gestion des Organisations et des Destinations Touristiques 1"));
        filiereRepository.save(new Filiere("GODT2","Gestion des Organisations et des Destinations Touristiques 2"));
        filiereRepository.save(new Filiere("GE1","Génie de l'Environnement 1"));
        filiereRepository.save(new Filiere("GE2","Génie de l'Environnement 2"));
        filiereRepository.save(new Filiere("IDSE1","Informatique Décisionnelle et Science de Données 1"));
        filiereRepository.save(new Filiere("IDSE2","Informatique Décisionnelle et Science de Données 2"));

        filiereRepository.save(new Filiere("LP-ISIL","LP Ingénieriedes Systemes Informatiques Et Logiciels"));
        filiereRepository.save(new Filiere("LP-MT","LP Management Bancaire et Financier"));
        filiereRepository.save(new Filiere("LP-ERDD","LP Energies Renouvelables et Développement Durable (ERDD)"));
        filiereRepository.save(new Filiere("LP-MBF","LP MBF"));
        filiereRepository.save(new Filiere("LP-MGE","LP Modélisation et Gestion de l'Environnement"));
*/

    }

}
