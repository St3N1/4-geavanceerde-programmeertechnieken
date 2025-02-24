package oefening_1_1;

public class Main {
    public static void main(String[] args) {
        Complex c1 = new Complex();
        Complex c2 = new Complex(1.0, 2.0);
        System.out.println(c1);
        System.out.println(c2);
        c1.setRe(5);
        c1.setIm(8);
        System.out.println(c1);
        System.out.println(c1.add(c2));
        System.out.println(c1.subtract(c2));
    }
}