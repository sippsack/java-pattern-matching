package de.sippsack.badtelefon.v5.tarif;

public record Privat(int rabatt) implements Tarif {
    public static final double PREISPROMINUTE = 1.99;
    public static final double MONDSCHEINPREISPROMINUTE = 0.69;

    public Privat {
        if (rabatt < 0 || rabatt > 100) {
            throw new IllegalArgumentException("Rabatt muss zwischen 0 und 100 % liegen");
        }
    }

    public Privat(){
        this(0);
    }

    public int getNettoMinuten(int minuten) {
        return Math.max(minuten - 1, 0);
    }
}
