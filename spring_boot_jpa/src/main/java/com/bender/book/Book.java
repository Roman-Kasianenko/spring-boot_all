package com.bender.book;

import com.bender.student.Student;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
public class Book {

    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "book_student_id_fk"),
            nullable = false,
            unique = true
    )
    private Student student;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;

    @Column(nullable = false)
    private ZonedDateTime createdAt;

    public Book() {
    }

    public Book(Student student, String title) {
        this.student = student;
        this.title = title;
    }

    @PrePersist
    void prePersist() {
        this.createdAt = ZonedDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Book{" +
                "createdAt=" + createdAt +
                ", id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
