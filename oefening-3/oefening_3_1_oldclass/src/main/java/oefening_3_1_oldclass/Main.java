package oefening_3_1_oldclass;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OldClass oldClass = new OldClass();
        oldClass.print();

        try {
            List<String> lines = Files
                    .readAllLines(Paths.get(
                            "C:\\Users\\stenh\\Desktop\\School\\2024-2025\\semester-2\\4-geavanceerde-programmeertechnieken\\oefening-3\\oefening_3_1_oldclass\\src\\main\\java\\oefening_3_1_oldclass\\oefening_3_1.txt"));

            System.out.println("JAR Path: " + lines.get(0));
            System.out.println("Class Name: " + lines.get(1));

            URLClassLoader ucl = new URLClassLoader(new URL[] { Paths.get(lines.get(0)).toUri().toURL() });
            Class<?> c = ucl.loadClass(lines.get(1));

            Object instance = c.getDeclaredConstructor().newInstance();
            c.getMethod("print").invoke(instance);

            ucl.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}