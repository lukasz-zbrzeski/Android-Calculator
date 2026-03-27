package com.example.calculator_v2;

import net.objecthunter.exp4j.function.Function;

public class TrigonometricFunction {

    public static Function[] values() {
        return new Function[] {
                new Function("Sin", 1) {
                    @Override
                    public double apply(double... args) {
                        return Math.sin(Math.toRadians(args[0]));
                    }
                },
                new Function("Cos", 1) {
                    @Override
                    public double apply(double... args) {
                        return Math.cos(Math.toRadians(args[0]));
                    }
                },
                new Function("Tan", 1) {
                    @Override
                    public double apply(double... args) {
                        return Math.tan(Math.toRadians(args[0]));
                    }
                },
                new Function("Ln", 1) {
                    @Override
                    public double apply(double... args) {
                        return Math.log(args[0]);
                    }
                },
                new Function("Log", 1) {
                    @Override
                    public double apply(double... args) {
                        return Math.log10(args[0]);
                    }
                },
                new Function("Exp", 1) {
                    @Override
                    public double apply(double... args) {
                        return Math.exp(args[0]);
                    }
                },
                new Function("Sqrt", 1) {
                    @Override
                    public double apply(double... args) {
                        return Math.sqrt(args[0]);
                    }
                },
                new Function("Sqr", 1) {
                    @Override
                    public double apply(double... args) {
                        return Math.pow(args[0], 2);
                    }
                }
        };
    }
}
