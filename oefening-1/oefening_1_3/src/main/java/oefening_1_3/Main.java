package oefening_1_3;

public class Main {
    public static void main(String[] args) {
        Persoon p1 = new Persoon("Jan", "5/7/2001", "Bomenlaan 19");
        Persoon p2 = new Student("Ann", "7/9/2000", "Perendreef 3", "2EI", 15);
        Persoon p3 = new Kotstudent("Jef", "3/5/2002", "Thuisadres 8", "2EI", 13, "Kotadres 872");

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
    }
}