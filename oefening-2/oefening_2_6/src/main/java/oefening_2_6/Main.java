package oefening_2_6;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Datum> datums = new ArrayList<>(5);

        datums.add(new Datum(5, 7, 2004));
        datums.add(new Datum(5, 8, 2005));
        datums.add(new Datum(7, 7, 2002));
        datums.add(new Datum(5, 7, 2008));
        datums.add(new Datum(5, 4, 2004));

        System.out.println("==== Unsorted ====");
        for (Datum datum : datums) {
            System.out.println(datum);
        }

        for (int i = 0; i < datums.size() - 1; i++) {
            for (int j = 0; j < datums.size() - 1 - i; j++) {
                if (datums.get(j).compareTo(datums.get(j + 1)) < 0) {
                    Datum temp = datums.get(j);
                    datums.set(j, datums.get(j + 1));
                    datums.set(j + 1, temp);
                }
            }
        }

        System.out.println("\n==== Sorted ====");
        for (Datum datum : datums) {
            System.out.println(datum);
        }

        System.out.println();

        ArrayList<Datum> datums2 = new ArrayList<>(10);

        datums2.add(new Datum(5, 7, 2004));
        datums2.add(new Datum(4, 8, 2019));
        datums2.add(new Datum(7, 7, 2017));
        datums2.add(new Datum(5, 12, 2008));
        datums2.add(new Datum(3, 4, 2023));
        datums2.add(new Datum(29, 5, 2004));
        datums2.add(new Datum(25, 11, 2010));
        datums2.add(new Datum(1, 8, 2016));
        datums2.add(new Datum(30, 12, 2011));
        datums2.add(new Datum(15, 10, 2000));
        datums2.add(new Datum(5, 12, 2023));

        long aantalJaar2023 = datums2.stream()
                .filter(datum -> datum.getJaar() == 2023).count();
        System.out.println("Aantal datums met jaar 2023: " + aantalJaar2023);

        long aantalMaand12 = datums2.stream()
                .filter(datum -> datum.getMaand() == 12).count();
        System.out.println("Aantal datums met jaar 2023: " + aantalMaand12);

        List<Datum> datumsMetJaar2023 = new ArrayList<>();
        datumsMetJaar2023 = datums2.stream()
                .filter(datum -> datum.getJaar() == 2023).toList();
        System.out.println("Datums met jaar 2023: " + datumsMetJaar2023);

        List<Datum> datumsTussen2015En2021 = new ArrayList<>();
        datumsTussen2015En2021 = datums2.stream()
                .filter(datum -> datum.getJaar() > 2015 && datum.getJaar() < 2021).toList();
        System.out.println("Datums tussen 2015 en 2021: " + datumsTussen2015En2021);

        List<Datum> datumsMetMaand7of8 = new ArrayList<>();
        datumsMetMaand7of8 = datums2.stream()
                .filter(datum -> datum.getMaand() == 7 || datum.getMaand() == 8).toList();
        System.out.println("Datums met maand 7 of 8: " + datumsMetMaand7of8);

        System.out.println();

        try {
            Datum d1 = new Datum(30, 12, 2024);
            Datum d2 = new Datum();
            Datum d3 = new Datum(31, 12, 2024);

            System.out.println(d1);
            System.out.println(d2);
            System.out.println(d3);

            d1.volgende();

            System.out.println(d1);

            d1.setDag(32);
        } catch (OngeldigeDatumException e) {
            e.printStackTrace();
        }
    }
}