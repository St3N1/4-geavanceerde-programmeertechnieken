package oefening_2_7;

public class Persoon {
    private String naam;
    private String gebdat;
    private String adres;

    // Constructors
    public Persoon(String naam, String gebdat, String adres) {
        this.naam = naam;
        this.gebdat = gebdat;
        this.adres = adres;
    }

    // Setters
    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setGebdat(String gebdat) {
        this.gebdat = gebdat;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    // Getters
    public String getNaam() {
        return naam;
    }

    public String getGebdat() {
        return gebdat;
    }

    public String getAdres() {
        return adres;
    }

    @Override
    public String toString() {
        return "Persoon(naam=" + this.naam + ", gebdat=" + this.gebdat + ", adres=" + this.adres + ")";
    }

}
