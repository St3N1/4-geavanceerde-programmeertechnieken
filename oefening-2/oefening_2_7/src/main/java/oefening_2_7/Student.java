package oefening_2_7;

public class Student extends Persoon {
    private String klas;
    private double punten;

    // Constructors
    public Student(String naam, String gebdat, String adres, String klas, double punten) {
        super(naam, gebdat, adres);
        this.klas = klas;
        this.punten = punten;
    }

    // Setters
    public void setKlas(String klas) {
        this.klas = klas;
    }

    public void setPunt(double punten) {
        this.punten = punten;
    }

    // Getters
    public String getKlas() {
        return klas;
    }

    public double getPunten() {
        return punten;
    }

    @Override
    public String toString() {
        return "Persoon(naam=" + this.getNaam() + ", gebdat=" + this.getGebdat() + ", adres=" + this.getAdres()
                + ", klas=" + this.klas + ", punten=" + this.punten + ")";
    }
}
