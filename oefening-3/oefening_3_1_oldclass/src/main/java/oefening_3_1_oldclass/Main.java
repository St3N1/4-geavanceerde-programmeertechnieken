package oefening_3_1_oldclass;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        OldClass oldClass = new OldClass();
        oldClass.print();

        try {
            Stream<String> file = Files
                    .lines(Paths.get("oefening-3/oefening_3_1_oldclass/src/main/java/oefening_3_1_oldclass/oefening_3_1.txt"));

            List<String> lines = new ArrayList<>();
            lines = file.toList();

            System.out.println("JAR Path: " + lines.get(0));
            System.out.println("Class Name: " + lines.get(1));
            
            URLClassLoader ucl = new URLClassLoader(new URL[] { Paths.get(lines.get(0)).toUri().toURL() });
            Class<?> c = ucl.loadClass(lines.get(1));

            Object instance = c.getDeclaredConstructor().newInstance();
            c.getMethod("print").invoke(instance);

            ucl.close();
            file.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}