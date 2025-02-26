package oefening_2_5;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    static boolean isPriemgetal(int n) {
        if (n <= 1)
            return false;

        for (int i = 2; i <= n / 2; i++)
            if (n % i == 0)
                return false;

        return true;
    }

    static Optional<List<Integer>> getPriemgetallen(int n1, int n2) {
        List<Integer> priemGetallen = new ArrayList<Integer>();
        for (int i = n1; i < n2; i++) {
            if (isPriemgetal(i))
                priemGetallen.add(i);
        }

        if (priemGetallen.size() == 0)
            return Optional.empty();

        return Optional.of(priemGetallen);
    }

    static Integer som(Optional<Integer> g1, Optional<Integer> g2) {
        System.out.println("g1 is aanwezig? " + g1.isPresent());
        System.out.println("g2 is aanwezig? " + g2.isPresent());
        Integer waarde1 = g1.orElse(0);
        Integer waarde2 = g2.orElse(0);
        return waarde1 + waarde2;
    }

    static void testSom() {
        Optional<Integer> getal1 = Optional.empty();
        Optional<Integer> getal2 = Optional.ofNullable(null);
        System.out.println("som van " + getal1 + " en " + getal2 + " is " + som(getal1, getal2));
        getal1 = Optional.of(7);
        getal2 = Optional.of(6);
        System.out.println("som van " + getal1 + " en " + getal2 + " is " + som(getal1, getal2));
    }

    public static void main(String[] args) {
        testSom();
        String naam = "Jef";
        if (naam != null)
            System.out.println("Hello " + naam);
        String naam2 = null;
        if (naam2 != null)
            System.out.println("Hello " + naam2);
        else
            System.out.println("naam2 is " + naam2);

        Optional<String> optionalNaam = Optional.of("Mia");
        optionalNaam.ifPresent(name -> System.out.println("Hello " + name));
        Optional<String> optionalNaam2 = Optional.ofNullable(null);
        optionalNaam2.ifPresent(name -> System.out.println("Hello " + name));
        optionalNaam2.ifPresentOrElse(
                name -> System.out.println("Hello " + name),
                () -> System.out.println("OptionalNaam2 is null"));
        Optional<String> optionalNaam3 = Optional.empty();
        optionalNaam3.ifPresent(name -> System.out.println("Hello " + name));
        optionalNaam3.ifPresentOrElse(
                name -> System.out.println("Hello " + name),
                () -> System.out.println("OptionalNaam3 is empty"));

        Optional<List<Integer>> priemgetallen1 = getPriemgetallen(2, 50);
        System.out.print("Priemgetallen(2..50): ");
        priemgetallen1.ifPresentOrElse(
                getallen -> System.out.println(getallen),
                () -> System.out.println("Het interval bevat geen priemgetallen!"));

        Optional<List<Integer>> priemgetallen2 = getPriemgetallen(32, 36);
        System.out.print("Priemgetallen(32..36): ");
        priemgetallen2.ifPresentOrElse(
                getallen -> System.out.println(getallen),
                () -> System.out.println("Het interval bevat geen priemgetallen!"));

    }
}
