package oefening_2_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>(); // Create arraylist
        list.add("Bie"); // Adding objects to arraylist
        list.add("An");
        list.add("Zeno");
        list.add("Daan");
        list.add("Bert");

        System.out.println("Traversing list with Iterator:");
        ListIterator<String> it = list.listIterator();
        while (it.hasNext()) {
            String str = it.next();
            System.out.println(str);
        }

        System.out.println("Traversing list with Iterator in reverse order:");
        ListIterator<String> itBack = list.listIterator(list.size());
        while (itBack.hasPrevious())
            System.out.println(itBack.previous());

        System.out.println("Traversing list with for loop:");
        for (int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));

        System.out.println("Traversing list with for loop:");
        for (var str : list)
            System.out.println(str);

        System.out.println("Traversing list using forEach() method and lambda:");
        list.forEach(str -> System.out.println(str));

        System.out.println("Traversing list using forEach() method and method reference:");
        list.forEach(System.out::println);

        Collections.sort(list);
        System.out.println("==== Sorted list ====");
        for (var str : list)
            System.out.print(str + " ");

        Collections.reverse(list);
        System.out.println("\n==== Reversed list ====");
        for (var str : list)
            System.out.print(str + " ");

        Collections.rotate(list, 1);
        System.out.println("\n==== Rotated list ====");
        for (var str : list)
            System.out.print(str + " ");

        Collections.shuffle(list);
        System.out.println("\n==== Shuffled list ====");
        for (var str : list)
            System.out.print(str + " ");
    }
}
