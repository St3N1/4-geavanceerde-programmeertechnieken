package oefening_2_6;

import java.time.LocalDate;

public class Datum implements Comparable<Datum> {
    private int dag;
    private int maand;
    private int jaar;

    // Constructors
    public Datum() {
        this.dag = LocalDate.now().getDayOfMonth();
        this.maand = LocalDate.now().getMonthValue();
        this.jaar = LocalDate.now().getYear();
    }

    public Datum(int dag, int maand, int jaar) {
        this.dag = dag;
        this.maand = maand;
        this.jaar = jaar;
    }

    // Setters
    public void setDag(int dag) throws OngeldigeDatumException {
        if (dag < 1) {
            throw new OngeldigeDatumException("Ongeldige dag in setDag! " + dag);
        }

        if ((this.maand == 4 || this.maand == 6 || this.maand == 9 || this.maand == 11) && dag > 30) {
            throw new OngeldigeDatumException("Ongeldige dag in setDag! " + dag);
        } else if (dag > 31) {
            throw new OngeldigeDatumException("Ongeldige dag in setDag! " + dag);
        }

        if (this.maand == 2) {
            if ((this.jaar % 4 == 0 && this.jaar % 100 != 0) || (this.jaar % 400 == 0)) {
                if (dag > 29) {
                    throw new OngeldigeDatumException("Ongeldige dag in setDag! " + dag);
                }
            } else {
                if (dag > 28) {
                    throw new OngeldigeDatumException("Ongeldige dag in setDag! " + dag);
                }
            }
        }

        this.dag = dag;
    }

    public void setMaand(int maand) throws OngeldigeDatumException {
        if (maand < 1 || maand > 12) {
            throw new OngeldigeDatumException("Ongeldige maand in setMaand! " + maand);
        }
        this.maand = maand;
    }

    public void setJaar(int jaar) {
        this.jaar = jaar;
    }

    // Getters
    public int getDag() {
        return this.dag;
    }

    public int getMaand() {
        return this.maand;
    }

    public int getJaar() {
        return this.jaar;
    }

    // Functions
    public void volgende() {
        // Check for end of year
        if (this.maand == 12) {
            if (this.dag == 31) {
                this.jaar++;
                this.maand = 1;
                this.dag = 1;
                return;
            }
        }

        // Check moths with 30 days
        if (this.maand == 4 || this.maand == 6 || this.maand == 9 || this.maand == 11) {
            if (this.dag == 30) {
                this.maand++;
                this.dag = 1;
                return;
            }
        }

        // Check months with 31 days
        if (this.maand == 1 || this.maand == 3 || this.maand == 5 || this.maand == 7 || this.maand == 8
                || this.maand == 10) {
            if (this.dag == 31) {
                this.maand++;
                this.dag = 1;
                return;
            }
        }

        // Check february
        if (this.maand == 2) {
            if ((this.jaar % 4 == 0 && this.jaar % 100 != 0) || (this.jaar % 400 == 0)) {
                if (this.dag == 29) {
                    this.maand++;
                    this.dag = 1;
                    return;
                }
            } else if (this.dag == 28) {
                this.maand++;
                this.dag = 1;
                return;
            }
        }

        this.dag++;
    }

    @Override
    public int compareTo(Datum d) {
        if (d.getJaar() == this.jaar && d.getMaand() == this.maand && d.getDag() == this.dag) {
            return 0;
        }

        if (d.getJaar() > this.jaar) {
            return 1;
        }

        if (d.getJaar() == this.jaar) {
            if (d.getMaand() > this.maand) {
                return 1;
            }
        }

        if (d.getJaar() == this.jaar && d.getMaand() == this.maand) {
            if (d.getDag() > this.dag) {
                return 1;
            }
        }

        return -1;
    }

    @Override
    public String toString() {
        return "Datum(dag=" + this.dag + ", maand=" + this.maand + ", jaar=" + this.jaar + ")";
    }
}
