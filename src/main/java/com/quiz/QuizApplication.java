package com.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * =====================================================
 * Point d'entrée principal de l'application Quiz
 * =====================================================
 * L'annotation @SpringBootApplication active :
 *  - @Configuration     : permet de définir des beans Spring
 *  - @EnableAutoConfiguration : configure automatiquement Spring selon les dépendances
 *  - @ComponentScan     : scanne les classes annotées (@Controller, @Service, etc.)
 */
@SpringBootApplication
public class QuizApplication {

    public static void main(String[] args) {
        // Lance le serveur embarqué Tomcat sur le port 8080 (par défaut)
        SpringApplication.run(QuizApplication.class, args);
        System.out.println("🎓 Quiz App démarrée ! Rendez-vous sur http://localhost:8080");
    }
}
