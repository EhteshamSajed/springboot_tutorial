package com.springboot_tutorial.springboot_tutorial.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        // return args->{
        //     new Student("Sajed", "me@email.com", LocalDate.of (2000, Month.JANUARY, 5));
        // };

        return new CommandLineRunner() {

            @Override
            public void run(String... args) throws Exception {
                // TODO Auto-generated method stub
                Student sajed = new Student("Sajed", "me@email.com", LocalDate.of (2000, Month.JANUARY, 5));
                Student jamal = new Student("Jamal", "jamal@email.com", LocalDate.of (1989, Month.JANUARY, 5));

                repository.saveAll(List.of (sajed, jamal));
            }            
        };
    }
}
