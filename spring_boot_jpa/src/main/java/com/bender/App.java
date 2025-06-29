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
import java.util.Set;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentIdCardRepository studentIdCardRepository,
            BookRepository bookRepository,
            StudentService studentService) {

        return args -> {
            oneToManyExample2(studentRepository, bookRepository);

        };
    }

    private static void oneToManyExample2(StudentRepository studentRepository, BookRepository bookRepository) {
        Student student = new Student(
                "Roman", "Kas", 35, "sdsd@Dsd.cd"
        );

        Book book = new Book();
        book.setTitle("Book 1");
        student.addBook(book);

//            Book book2 = new Book();
//            book2.setTitle("Book 2");
//            student.addBook(book2);

        student = studentRepository.save(student);

        System.out.println(bookRepository.count());

        studentRepository.selectStudentsWithBooks().forEach(s -> {
            System.out.println("Student - " + s.getFirstName());
            s.getBooks().forEach(b -> {
                System.out.println("Book: " + b.getTitle());
                System.out.println("Book student: " + b.getStudent());
            });
        });

        student.removeBook(book);
        student = studentRepository.save(student);

        System.out.println(bookRepository.count());

        studentRepository.selectStudentsWithBooks().forEach(s -> {
            System.out.println("Student - " + s.getFirstName());
            System.out.println("Books size: " + s.getBooks().size());
            s.getBooks().forEach(b -> {
                System.out.println("Book: " + b.getTitle());
                System.out.println("Book student: " + b.getStudent());
            });
        });
    }

    private static void oneToManeExample(StudentRepository studentRepository, BookRepository bookRepository, StudentService studentService) {
        Student student = new Student(
                "Roman", "Kas", 35, "sdsd@Dsd.cd"
        );

        Book book = new Book();
        book.setStudent(student);
        book.setTitle("Book 1");

        student.setBooks(Set.of(book));

        studentRepository.save(student);

        // all books
        System.out.println("All books:\n------------------------------------------");
        bookRepository.findAll().forEach(book1 -> {
            System.out.println("Book Title" + book1.getTitle());
            System.out.println("\t Student name" + book1.getStudent().getFirstName());
            System.out.println("\t Created At" + book1.getCreatedAt());
        });

        studentService.getStudentWithBooks(student.getId()).ifPresent(s-> {
            System.out.println("Student - " + s.getFirstName());
            s.getBooks().forEach(book2 -> {
                System.out.println("Book: " + book2.getTitle());
            });
        });

        // all students
//            System.out.println("\nAll students:\n---------------------------------------");
//            studentRepository.selectStudentsWithBooks().forEach(s ->{
//                System.out.println("Student - " + s.getFirstName());
//                s.getBooks().forEach(book2 -> {
//                    System.out.println("Book: " + book2.getTitle());
//                });
//            });

    }

    private static void exercise1(StudentRepository studentRepository, BookRepository bookRepository) {
        Student student = new Student(
                "Roman", "Kas", 35, "sdsd@Dsd.cd"
        );
        student = studentRepository.save(student);

        Book book = new Book();
        book.setStudent(student);
        book.setTitle("Book 1");

        book = bookRepository.save(book);

        Student student2 = new Student(
                "Yana", "Kas", 35, "yana@Dsd.cd"
        );
        student2 = studentRepository.save(student2);

        Book book2 = new Book();
        book2.setStudent(student2);
        book2.setTitle("Book 1");

        book2 = bookRepository.save(book2);

        bookRepository.findAll().forEach(book1 -> {
            System.out.println(book1.getTitle());
            System.out.println(book1.getStudent().getFirstName());
            System.out.println(book1.getCreatedAt());
        });

        System.out.println("Total books before delete - " + bookRepository.count());
        System.out.println("Total students before delete - " + studentRepository.count());

        bookRepository.deleteAll();

        System.out.println("Total books after delete - " + bookRepository.count());
        System.out.println("Total students after delete - " + studentRepository.count());
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
