package model;

public class Functions {
    public static double sigmoidFunc(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public static double linearFunc(double x) {
        return x;
    }
}