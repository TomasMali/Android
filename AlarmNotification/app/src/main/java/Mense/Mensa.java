package Mense;

public class Mensa {
    private String mensa;
    private String pranzo;
    private String cena;

    public Mensa(String mensa, String pranzo, String cena) {
        this.mensa = mensa;
        this.pranzo = pranzo;
        this.cena = cena;
    }

    public String getMensa() {
        return mensa;
    }

    public String getPranzo() {
        return pranzo;
    }

    public String getCena() {
        return cena;
    }

    @Override
    public String toString() {
        return "Mensa{" +
                "mensa='" + mensa + '\'' +
                ", pranzo='" + pranzo + '\'' +
                ", cena='" + cena + '\'' +
                '}';
    }
}
