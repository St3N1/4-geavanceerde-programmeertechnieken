package oefening_1_2;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Datum> datums = new ArrayList<>();

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