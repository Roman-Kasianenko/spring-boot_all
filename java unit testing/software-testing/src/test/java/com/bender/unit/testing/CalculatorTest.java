package com.bender.unit.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CalculatorTest {

    private Calculator underTest;

    @BeforeEach
    void setUp() {
        System.out.println("setUp");
        underTest = new Calculator();
    }

    @Test
    void canAddNumber() {
        // 1 - given
        var number1 = 2;
        var number2 = 3;
        // 2 - when
        var result = underTest.add(number1, number2);
        // then
        var expected = number1 + number2;
        assertThat(result).isEqualTo(expected);
    }


}