package com.bender.unit.testing.exercises;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class Exercise1Test {

    private final Exercise1 underTest = new Exercise1();

    @ParameterizedTest
    @CsvSource({
            "90, A",
            "91, A",
            "100, A",
            "80, B",
            "81, B",
            "89, B",
            "70, C",
            "71, C",
            "79, C",
            "60, D",
            "61, D",
            "69, D",
            "50, E",
            "51, E",
            "59, E",
            "49, F",
            "0, F",
            "1, F"
    })
    void canGetCorrectGrade(int grade, String expectedGrade ) {
        //1 - given
        //2 - when
        String actualResult = underTest.getGrade(grade);
        //3 - then
        assertThat(actualResult).isEqualTo(expectedGrade);
    }

    @ParameterizedTest
    @CsvSource({
            "-1",
            "1000"
    })
    void willThrowWhenInvalidGrade(int score) {
        assertThatThrownBy(() -> underTest.getGrade(score))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Score must be between 0 and 100");
    }

    @ParameterizedTest
    @CsvSource({
            "Roman, 2",
            ",0"
    })
    void countVowels(String name, int expectedVowels) {
        //1 - given
        //2 - when
        int actual = underTest.countVowels(name);
        //3 - then
        assertThat(actual).isEqualTo(expectedVowels);
    }

    @ParameterizedTest
    @CsvSource({
            "S1234,true",
            "S12341,false",
            "1234,false",
            ",false",
    })
    void isValidStudentId(String studentId, boolean expectedIsValid) {
        //1 - given
        //2 - when
        boolean actual = underTest.isValidStudentId(studentId);
        //3 - then
        assertThat(actual).isEqualTo(expectedIsValid);
    }

    @Test
    void canCalculateAverage() {
        //1 - given
        var scores = List.of(1, 2, 3, 4, 5);
        //2 - when
        double actualResult = underTest.calculateAverage(scores);
        //3 - then
        int expectedResult = 3;
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void canCalculateAverageWhenNull() {
        //1 - given
        List<Integer> scores = null;
        //2 - when
        double actualResult = underTest.calculateAverage(scores);
        //3 - then
        double expectedResult = 0.0;
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void canCalculateAverageWhenEmpty() {
        //1 - given
        List<Integer> scores = List.of();
        //2 - when
        double actualResult = underTest.calculateAverage(scores);
        //3 - then
        double expectedResult = 0.0;
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "Roman K, rk",
            "User,''",
            ",''"
    })
    void generateUsername(String username, String expectedUsername) {
        //1 - given
        //2 - when
        String actualUsername = underTest.generateUsername(username);
        //3 - then
        assertThat(actualUsername).isEqualTo(expectedUsername);
    }

    @Test
    void canGetTopStudents() {
        //1 - given
        List<Student> students = List.of(
                new Student("Student1", 100),
                new Student("Student2", 10),
                new Student("Student3", 80),
                new Student("Student4", 54),
                new Student("Student5", 18)
        );
        //2 - when
        int threshold = 50;
        List<Student> expected = List.of(
                new Student("Student1", 100),
                new Student("Student3", 80),
                new Student("Student4", 54)
        );
        List<Student> actual = underTest.getTopStudents(students, threshold);
        //3 - then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void willReturnEmptyListWhenStudentsNull() {
        //1 - given
        List<Student> students = null;
        //2 - when
        int threshold = 50;
        List<Student> expected = List.of();
        List<Student> actual = underTest.getTopStudents(students, threshold);
        //3 - then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void hasDuplicateNames() {
        //1 - given
        List<Student> students = List.of(
                new Student("Student1", 100),
                new Student("Student1", 10),
                new Student("Student3", 80),
                new Student("Student4", 54),
                new Student("Student5", 18)
        );
        //2 - when

        boolean actual = underTest.hasDuplicateNames(students);
        //3 - then
        assertThat(actual).isTrue();
    }

    @Test
    void hasDuplicateNamesWhenOne() {
        //1 - given
        List<Student> students = List.of(
                new Student("Student1", 100)
        );
        //2 - when

        boolean actual = underTest.hasDuplicateNames(students);
        //3 - then
        assertThat(actual).isFalse();
    }

    @Test
    void hasDuplicateNamesWhenNull() {
        //1 - given
        List<Student> students = null;
        //2 - when
        boolean actual = underTest.hasDuplicateNames(students);
        //3 - then
        assertThat(actual).isFalse();
    }

    @Test
    void hasDuplicateNamesWhenEmptyList() {
        //1 - given
        List<Student> students = List.of();
        //2 - when
        boolean actual = underTest.hasDuplicateNames(students);
        //3 - then
        assertThat(actual).isFalse();
    }


    @Test
    void reverseCourses() {
        //1 - given
        List<String> courses = List.of(
                "Course 1", "Course 2", "Course 3", "Course 4", "Course 5"
        );
        //2 - when
        List<String> reverseCourses = underTest.reverseCourses(courses);
        //3 - then
        List<String> expectedCourses = List.of(
                "Course 5", "Course 4", "Course 3", "Course 2", "Course 1"
        );
        assertThat(reverseCourses).isEqualTo(expectedCourses);
    }

    @Test
    void reverseCoursesWhenNullReturnEmptyList() {
        //1 - given
        List<String> courses = null;
        //2 - when
        List<String> reverseCourses = underTest.reverseCourses(courses);
        //3 - then
        List<String> expectedCourses = new ArrayList<>();
        assertThat(reverseCourses).isEqualTo(expectedCourses);
    }

    @ParameterizedTest
    @CsvSource({
            "40, false",
            "50,true",
            "100, true",
            "49,false"
    })
    void hasPassed(int score, boolean expectedPassed) {
        //1 - given
        //2 - when
        boolean actual = underTest.hasPassed(score);
        //3 - then
        assertThat(actual).isEqualTo(expectedPassed);
    }

    @ParameterizedTest
    @CsvSource({
            "true,Star Student",
            "false,Needs Improvement"
    })
    void assignBadge(boolean isHelpful, String expectedBadge) {
        //1 - given
        //2 - when
        String actual = underTest.assignBadge(isHelpful);
        //3 - then
        assertThat(actual).isEqualTo(expectedBadge);
    }
}