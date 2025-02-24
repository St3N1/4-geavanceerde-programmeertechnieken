package oefening_1_4;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Student st1 = new Student("Jan", "05/07/2004", "Adres", "2EI", 13, 15, 12);
        st1.setPunten(0, 15); // punten voor vak 0 op 15
        System.out.println(st1);
        System.out.println("Het gemiddelde is " + st1.gemiddelde());

        ArrayList<Student> studenten = new ArrayList<>();
        studenten.add(new Student("Dan", "05/07/2004", "Adres", "2EI", 14, 11, 12));
        studenten.add(new Student("Mie", "03/04/2002", "Adres", "2EI", 16, 18, 16));
        studenten.add(new Student("An", "25/06/2003", "Adres", "2EI", 17, 18, 11));

        System.out.println("\n==== Unsorted ====");
        for (Student student : studenten) {
            System.out.println(student);
        }

        for (int i = 0; i < studenten.size() - 1; i++) {
            for (int j = 0; j < studenten.size() - 1 - i; j++) {
                if (studenten.get(j).compare(studenten.get(j), studenten.get(j + 1)) > 0) {
                    Student temp = studenten.get(j);
                    studenten.set(j, studenten.get(j + 1));
                    studenten.set(j + 1, temp);
                }
            }
        }

        System.out.println("\n==== Sorted ====");
        for (Student student : studenten) {
            System.out.println(student);
        }

    }
}