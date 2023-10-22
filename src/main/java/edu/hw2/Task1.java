package edu.hw2;

import edu.hw2.Task1.Expr.Addition;
import edu.hw2.Task1.Expr.Constant;
import edu.hw2.Task1.Expr.Exponent;
import edu.hw2.Task1.Expr.Multiplication;
import edu.hw2.Task1.Expr.Negate;

public class Task1 {

    public String check(int value1, int value2, int value3, int valueRate, int value4) {
        var two = new Constant(value1);
        var four = new Constant(value2);
        var negOne = new Negate(new Constant(value3));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, valueRate);
        var res = new Addition(exp, new Constant(value4));

        return res + " = " + res.evaluate();
    }

    public sealed interface Expr {
        double evaluate();

        record Constant(double value) implements Expr {

            @Override
            public double evaluate() {
                return value;
            }
        }

        record Negate(Expr constant) implements Expr {

            @Override
            public double evaluate() {
                return -constant.evaluate();
            }
        }

        record Exponent(Expr constant, int rate) implements Expr {

            @Override
            public double evaluate() {
                return Math.pow(constant.evaluate(), rate);
            }
        }

        record Addition(Expr firstConstant, Expr secondConstant) implements Expr {

            @Override
            public double evaluate() {
                return firstConstant.evaluate() + secondConstant().evaluate();
            }
        }

        record Multiplication(Expr firstConstant, Expr secondConstant) implements Expr {

            @Override
            public double evaluate() {
                return firstConstant.evaluate() * secondConstant().evaluate();
            }
        }
    }
}
