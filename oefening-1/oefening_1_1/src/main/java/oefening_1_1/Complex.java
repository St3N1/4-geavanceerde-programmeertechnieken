package oefening_1_1;

public class Complex {
    private double re;
    private double im;

    // Constructors
    public Complex() {
        this.re = 0.0;
        this.im = 0.0;
    }

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    // Setters
    public void setRe(double re) {
        this.re = re;
    }

    public void setIm(double im) {
        this.im = im;
    }

    // Getters
    public double getRe() {
        return this.re;
    }

    public double getIm() {
        return this.im;
    }

    // Math
    public Complex add(Complex c) {
        this.re += c.re;
        this.im += c.im;
        return this;
    }

    public Complex subtract(Complex c) {
        this.re -= c.re;
        this.im -= c.im;
        return this;
    }

    @Override
    public String toString() {
        return "Complex( " + this.re + ", " + this.im + " )";
    }
}
