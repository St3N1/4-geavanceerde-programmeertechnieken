package oefening_2_4;

public class VogelTelling {
    private String naam;
    private int aantal;

    public VogelTelling(String naam, int aantal) {
        this.naam = naam;
        this.aantal = aantal;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    @Override
    public String toString() {
        return "VogelTelling(" + "naam='" + naam + '\'' + ", aantal=" + aantal + ')';
    }
}
