package oefening_1_4;

import java.util.ArrayList;
import java.util.Comparator;

public class Student extends Persoon implements Comparator<Student> {
    private String klas;
    private ArrayList<Integer> punten = new ArrayList<>(3);

    // Constructors
    public Student(String naam, String gebdat, String adres, String klas, int punt1, int punt2, int punt3) {
        super(naam, gebdat, adres);
        this.klas = klas;

        this.punten.add(punt1);
        this.punten.add(punt2);
        this.punten.add(punt3);
    }

    // Setters
    public void setKlas(String klas) {
        this.klas = klas;
    }

    public void setPunten(int index, int punt) {
        if (index < 0 || index > 2) {
            throw new IndexOutOfBoundsException("Index must be 0, 1, or 2");
        }

        punten.set(index, punt);
    }

    // Getters
    public String getKlas() {
        return klas;
    }

    public int getPunten(int index) {
        if (index < 0 || index > 2) {
            throw new IndexOutOfBoundsException("Index must be 0, 1, or 2");
        }

        return punten.get(index);
    }

    // Functions
    public int gemiddelde() {
        int som = 0;
        for (int punt : punten) {
            som += punt;
        }

        return som / punten.size();
    }

    @Override
    public int compare(Student st1, Student st2) {
        if (st1.gemiddelde() < st2.gemiddelde()) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Persoon(naam=" + this.getNaam() + ", gebdat=" + this.getGebdat() + ", adres=" + this.getAdres()
                + ", klas=" + this.klas + ", punten=" + this.punten + ", gemiddelde=" + this.gemiddelde() + ")";
    }

}
