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

import java.util.Optional;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository, StudentIdCardRepository studentIdCardRepository) {

        return args -> {
//            exampleOneByOne2(studentRepository);
//            exampleOneToOne(studentRepository, studentIdCardRepository);
//            generateRandomStudents(studentRepository);
//
//            System.out.println(studentRepository.count());

//            pageable(studentRepository);
//            sorted(studentRepository);
        };
    }

    private static void exampleOneByOne2(StudentRepository studentRepository) {
        Student student = new Student(
                "Roman", "Kas", 35, "sdsd@Dsd.cd"
        );
        StudentIdCard studentIdCard = new StudentIdCard();
        studentIdCard.setCardNumber("123456789");
        studentIdCard.setStudent(student);

        student.setStudentIdCard(studentIdCard);
        studentRepository.save(student);

        Optional<Student> s = studentRepository.findById(1L);
        System.out.println(s.get().getStudentIdCard());
    }

    private static void exampleOneToOne(StudentRepository studentRepository, StudentIdCardRepository studentIdCardRepository) {
        Student student = new Student(
                "Roman", "Kas", 35, "sdsd@Dsd.cd"
        );
        student = studentRepository.save(student);
        StudentIdCard studentIdCard = new StudentIdCard();
        studentIdCard.setCardNumber("123456789");
        studentIdCard.setStudent(student);
        studentIdCardRepository.save(studentIdCard);

        Optional<StudentIdCard> cardRepositoryById = studentIdCardRepository.findStudentIdCardByIdWithStudent(1L);
        System.out.println(cardRepositoryById.get().getId());
        System.out.println(cardRepositoryById.get().getCardNumber());
        System.out.println(cardRepositoryById.get().getStudent());
        System.out.println(cardRepositoryById.get().getCreatedAt());
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
        for (int i = 1; i <= 2; i++) {
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
