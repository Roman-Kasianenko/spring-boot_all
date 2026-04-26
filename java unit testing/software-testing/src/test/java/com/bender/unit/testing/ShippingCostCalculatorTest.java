package com.bender.unit.testing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShippingCostCalculatorTest {

    private final ShippingCostCalculator underTest = new ShippingCostCalculator();

    @Test
    void shouldChargeFiveEurosForSmallPackage() {
        double actual = underTest.calculate(0.5, "Local", false);
        assertThat(actual).isEqualTo(5.00);
    }

    @Test
    void shouldChargeTenEurosForLocalStandardShipping() {
        double actual = underTest.calculate(2.0, "Local", false);
        assertThat(actual).isEqualTo(10.00);
    }

    @Test
    void shouldChargeTwentyEurosForInternationalShipping() {
        double actual = underTest.calculate(2.0, "International", false);
        assertThat(actual).isEqualTo(20.00);
    }

    @Test
    void shouldAddExpressFee() {
        double actual = underTest.calculate(0.5, "Local", true);
        assertThat(actual).isEqualTo(20.00);
    }
}
