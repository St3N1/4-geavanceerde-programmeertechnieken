package oefening_1_2;

public class Main {
    public static void main(String[] args) {
        Datum[] datums = {
                new Datum(5, 7, 2004),
                new Datum(5, 8, 2005),
                new Datum(7, 7, 2002),
                new Datum(5, 7, 2008),
                new Datum(5, 4, 2004),
        };

        System.out.println("==== Unsorted ====");
        for (Datum datum : datums) {
            System.out.println(datum);
        }

        for (int i = 0; i < datums.length - 1; i++) {
            for (int j = 0; j < datums.length - 1 - i; j++) {
                if (datums[j].compareTo(datums[j + 1]) > 0) {
                    Datum temp = datums[j];
                    datums[j] = datums[j + 1];
                    datums[j + 1] = temp;
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