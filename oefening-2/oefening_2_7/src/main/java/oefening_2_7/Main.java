package oefening_2_7;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Persoon p1 = new Persoon("Jan", "5/7/2001", "Bomenlaan 19");
        Persoon p2 = new Student("Ann", "7/9/2000", "Perendreef 3", "2EI", 15);
        Persoon p3 = new Kotstudent("Jef", "3/5/2002", "Thuisadres 8", "2EI", 13, "Kotadres 872");

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        List<Student> studenten = new ArrayList<Student>(5);
        studenten.add(new Student("Jan", "5/2/2000", "A1", "2EI", 88));
        studenten.add(new Student("Ben", "9/8/2002", "A2", "2EI", 62));
        studenten.add(new Student("Mie", "14/12/2002", "A3", "2EI", 35));
        studenten.add(new Student("Bert", "25/6/2001", "A4", "2EI", 85));
        studenten.add(new Student("An", "4/4/2000", "A5", "2EI", 45));

        Long aantalStudentenBoven85 = studenten.stream()
                .filter(student -> student.getPunten() >= 85)
                .count();
        System.out.println("Aantal studenten met punten hoger of gelijk aan 85: " + aantalStudentenBoven85);

        Long aantalStudentenBoven50 = studenten.stream()
                .filter(student -> student.getPunten() >= 50)
                .count();
        System.out.println("Aantal studenten met punten hoger of gelijk aan 85: " + aantalStudentenBoven50);

        List<Student> studentenBoven50 = new ArrayList<>();
        studentenBoven50 = studenten.stream()
                .filter(student -> student.getPunten() >= 50)
                .toList();
        System.out.println("Studenten met punten hoger of gelijk aan 50: " + studentenBoven50);

        List<Student> studentenOnder50 = new ArrayList<>();
        studentenOnder50 = studenten.stream()
                .filter(student -> student.getPunten() < 50)
                .toList();
        System.out.println("Studenten met punten hoger of gelijk aan 50: " + studentenOnder50);

        List<Student> studentenBoven75EnKlas2EI = new ArrayList<>();
        studentenBoven75EnKlas2EI = studenten.stream()
                .filter(student -> student.getPunten() >= 75 && student.getKlas() == "2EI")
                .toList();
        System.out.println("Studenten met punten hoger of gelijk aan 75 en klas 2EI: " + studentenBoven75EnKlas2EI);

    }
}