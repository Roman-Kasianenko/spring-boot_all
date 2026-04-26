package com.bender.unit.testing;

public class ShippingCostCalculator {
    public double   calculate(double weightKg, String region, boolean express) {
        double cost = 5.0;
        if (weightKg >= 1 && weightKg <= 5) {
            if (region.equals("Local")) {
                cost = 10.0;
            }

            if (region.equals("International")) {
                cost = 20.0;
            }

        }

        if (express) {
            cost += 15.0;
        }

        return cost;
    }
}