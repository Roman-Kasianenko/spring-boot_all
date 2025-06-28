package com.bender;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {

        return args -> {
            generateRandomStudents(studentRepository);

            System.out.println(studentRepository.count());

//            pageable(studentRepository);
//            sorted(studentRepository);
        };
    }

    private static void pageable(StudentRepository studentRepository) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Student> page = studentRepository.findAll(pageable);
        System.out.println(page.getTotalPages());
        System.out.println(page.getTotalElements());
        System.out.println(page.getSize());
        page.get().forEach(System.out::println);
    }

    private static void sorted(StudentRepository studentRepository) {
        Sort sort = Sort.by(Sort.Direction.ASC, "firstName");

        studentRepository.findAll(sort)
                .forEach(student ->  System.out.println(student.getFirstName() + " - " + student.getAge()));
    }

    private static void generateRandomStudents(StudentRepository studentRepository) {
        Faker faker = new Faker();
        for (int i = 1; i <= 100; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Integer age = faker.number().numberBetween(17, 60);
            String email = """
                    %s.%s@gmail.com
                    """.formatted(firstName, lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    age,
                    email
            );
            studentRepository.save(student);
        }
    }

}
