package oefening_2_7;

public class Kotstudent extends Student {
    private String kotadres;

    // Constructors
    public Kotstudent(String naam, String gebdat, String adres, String klas, double punten, String kotadres) {
        super(naam, gebdat, adres, klas, punten);
        this.kotadres = kotadres;
    }

    // Setters
    public void setKotadres(String kotadres) {
        this.kotadres = kotadres;
    }

    // Getters
    public String getKotadres() {
        return kotadres;
    }

    @Override
    public String toString() {
        return "Persoon(naam=" + this.getNaam() + ", gebdat=" + this.getGebdat() + ", adres=" + this.getAdres()
                + ", klas=" + this.getKlas() + ", punten=" + this.getPunten() + ", kotadres=" + this.kotadres +")";
    }
}
