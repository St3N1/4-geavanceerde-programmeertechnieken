package oefening_2_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    static boolean isPriemgetal(int n) {
        if (n <= 1)
            return false;

        for (int i = 2; i <= n / 2; i++)
            if (n % i == 0)
                return false;

        return true;
    }

    static void voorbeelden() {
        List<String> strlijst = List.of("mia", "jan", "jef");
        System.out.println("strlijst: " + strlijst);

        strlijst.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        strlijst.stream()
                .map(s -> s.toUpperCase())
                .forEach(s -> System.out.print(s + ' '));

        System.out.println("\nTest Intstream met peek: " +
                IntStream.of(1, 2, 3, 4)
                        .filter(e -> e > 2)
                        .peek(e -> System.out.println("Filtered value: " + e))
                        .map(e -> e * e)
                        .peek(e -> System.out.println("Mapped value: " + e))
                        .sum());

        System.out.println("Druk de kwadraten van alle oneven getallen tussen 1 en 9:");
        IntStream.range(1, 10)
                .filter(i -> i % 2 != 0)
                .map(i -> i * i)
                .forEach(i -> System.out.print(i + " "));

        System.out.println("\nMaak een List met de kwadraten van de even getallen tussen 10 en 19:");
        List<Integer> kwadList = IntStream.range(10, 20)
                .filter(i -> i % 2 == 0)
                .map(i -> i * i)
                .mapToObj(i -> Integer.valueOf(i)) // .boxed()
                .collect(Collectors.toList());
        System.out.println(kwadList);

        int kwadraatSom = IntStream.range(1, 51)
                .filter(i -> i % 2 == 0)
                .map(i -> (i * i))
                .reduce(0, Integer::sum);
        System.out.println("\nSom van kwadraten van even getallen tussen 1 en 50: " + kwadraatSom + "\n");

        List<Integer> priemGetallen = IntStream.range(2, 100)
                .filter(i -> isPriemgetal(i))
                .boxed()
                .toList();

        System.out.println("Priemgetallen tussen 2 en 100: " + priemGetallen + "\n");

        int priemGetallenSom = IntStream.range(100, 200)
                .filter(i -> isPriemgetal(i)).reduce(0, Integer::sum);
        System.out.println("Som van priemgetallen tussen 100 en 200: " + priemGetallenSom + "\n");
    }

    static void reduceVoorbeelden() {
        System.out.println("Reduce voorbeelden:");
        int count = List.of(2, 3, 4)
                .stream()
                .reduce(0, (acc, cur) -> acc + 1);
        System.out.println("count: " + count);
        int sum = List.of(2, 3, 4)
                .stream()
                .reduce(0, (acc, cur) -> acc + cur);
        System.out.println("sum  : " + sum);
        int min = List.of(10, 15, 11)
                .stream()
                .reduce(Integer.MAX_VALUE, (acc, cur) -> acc < cur ? acc : cur);
        System.out.println("min  : " + min);
        int max = List.of(10, 15, 11)
                .stream()
                .reduce(Integer.MIN_VALUE, (acc, cur) -> acc > cur ? acc : cur);
        System.out.println("max  : " + max);

        long aantalPriemgetallen = IntStream.range(100, 150)
                .filter(i -> isPriemgetal(i))
                .count();
        System.out.println("\nAantal priemgetallen tussen 100 en 150: " + aantalPriemgetallen + "\n");

        int productOneven = IntStream.range(6, 18)
                .filter(i -> i % 2 != 0)
                .reduce(1, (acc, cur) -> acc *= cur);
        System.out.println("\nProduct van oneven getallen tussen 6 en 17: " + productOneven + "\n");

        int productPriemgetallen = IntStream.range(5, 21)
                .filter(i -> isPriemgetal(i))
                .reduce(1, (acc, cur) -> acc *= cur);
        System.out.println("\nProduct van priemgetallen tussen 5 en 20: " + productPriemgetallen + "\n");
    }

    static ArrayList<VogelTelling> leesVogeltellingGegevens(String bestandNaam) {
        ArrayList<VogelTelling> lijst = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(bestandNaam)); // creates a buffering
                                                                                     // character
                                                                                     // input stream
            String line = reader.readLine();
            while (line != null) {
                String telling[] = line.split(" ");
                lijst.add(new VogelTelling(telling[0], Integer.parseInt(telling[1]))); // voeg vogel
                                                                                       // telling toe
                line = reader.readLine();
            }
            reader.close(); // closes the stream and release the resources
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return lijst;
    }

    public static void main(String[] args) {
        voorbeelden();
        reduceVoorbeelden();

        ArrayList<VogelTelling> lijst = leesVogeltellingGegevens(
                "oefening-2\\oefening_2_4\\src\\main\\java\\oefening_2_4\\telling.txt");

        lijst.forEach(System.out::println);
        long aantalTellingen = lijst.stream()
                .filter(telling -> telling.getNaam().equals("koolmees"))
                .count();

        System.out.println("Aantal tellingen koolmezen: " + aantalTellingen);
        int totaalaantal = lijst.stream()
                .filter(telling -> telling.getNaam().equals("koolmees"))
                .map(telling -> telling.getAantal())
                .reduce(0, (total, aantal) -> total + aantal);

        System.out.println("Totaal aantal koolmezen: " + totaalaantal);
        int totaantal = 0;
        for (VogelTelling telling : lijst)
            if (telling.getNaam().equals("koolmees")) {
                int aantal = telling.getAantal();
                totaantal = totaantal + aantal;
            }

        System.out.println("Totaal aantal koolmezen: " + totaantal);
        int totaant = lijst.stream()
                .filter(telling -> telling.getNaam().equals("koolmees"))
                .map(telling -> telling.getAantal())
                .reduce(0, Integer::sum);

        System.out.println("Totaal aantal koolmezen: " + totaant);

        int totaalVogels = lijst.stream()
                .map(telling -> telling.getAantal())
                .reduce(0, Integer::sum);
        System.out.println("Totaal aantal vogels: " + totaalVogels);

        int totaalVinken = lijst.stream()
                .filter(telling -> telling.getNaam().equals("vink"))
                .map(telling -> telling.getAantal())
                .reduce(0, Integer::sum);
        System.out.println("Totaal aantal vinken: " + totaalVinken);

        int totaalKauwenEnHuismussen = lijst.stream()
                .filter(telling -> telling.getNaam().equals("kauw")
                        || telling.getNaam().equals("huismus"))
                .map(telling -> telling.getAantal())
                .reduce(0, Integer::sum);
        System.out.println("Totaal aantal kauwen en huismussen: " + totaalKauwenEnHuismussen);
    }
}
