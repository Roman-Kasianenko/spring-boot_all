package com.bender.student;

import com.bender.book.Book;
import com.bender.course.Course;
import com.bender.courseenrollment.CourseEnrollment;
import com.bender.studentidcard.StudentIdCard;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
@SQLDelete(
        sql = "update student set deleted_at = now() where id = ?"
)
@SQLRestriction(
        "deleted_at is null"
)
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String firstName;

    @Column(columnDefinition = "TEXT")
    private String lastName;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    private StudentIdCard studentIdCard;

    @OneToMany(
            mappedBy = "student",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
            //fetch = FetchType.EAGER // TODO could produce performance issue in case of a lot of records
    )
    private Set<Book> books = new HashSet<>();

    @OneToMany(
            cascade = {CascadeType.PERSIST},
            mappedBy = "student",
            orphanRemoval = true
    )
    private Set<CourseEnrollment> courseEnrollments = new HashSet<>();

    private ZonedDateTime deletedAt;

    public Student() {
    }

    public Student(String firstName,
                   String lastName,
                   Integer age,
                   String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StudentIdCard getStudentIdCard() {
        return studentIdCard;
    }

    public void setStudentIdCard(StudentIdCard studentIdCard) {
        this.studentIdCard = studentIdCard;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Set<CourseEnrollment> getCourseEnrollments() {
        return courseEnrollments;
    }

    public void setCourseEnrollments(Set<CourseEnrollment> courseEnrollments) {
        this.courseEnrollments = courseEnrollments;
    }

    public void addCourseEnrollment(Course course) {
        courseEnrollments.add(new CourseEnrollment(this, course));
    }
    public void removeCourseEnrollment(Course course) {
        courseEnrollments.removeIf(
                courseEnrollment -> courseEnrollment.getCourse().equals(course));
    }

    public void addBook(Book book) {
        if (!this.books.contains(book)) {
            this.books.add(book);
            book.setStudent(this);
        }
    }

    public void removeBook(Book book) {
        if (this.books.contains(book)) {
            this.books.remove(book);
            book.setStudent(null);
        }
    }

    public ZonedDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(ZonedDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(age, student.age) && Objects.equals(email, student.email) && Objects.equals(studentIdCard, student.studentIdCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, email, studentIdCard);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
