package de.sippsack.badtelefon.v5;

import java.util.Comparator;

public record Zeitpunkt(int stunde, int minute) implements Comparable<Zeitpunkt>, Comparator<Zeitpunkt> {

    public static final int MONDSCHEINZEIT_ENDE = 9;
    public static final int MONDSCHEINZEIT_ANFANG = 18;

    public Zeitpunkt {
        if ((stunde < 0 || stunde > 23) && minute < 0 || minute > 59) {
            throw new IllegalArgumentException(String.format("Ungültiger Zeitpunkt: %s", this));
        }
    }

    private Zeitpunkt(int minutenVonHeute) {
        this(minutenVonHeute / 60, minutenVonHeute % 60);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d Uhr", stunde, minute);
    }

    private int minutenSeitHeute() {
        return this.stunde * 60 + this.minute;
    }

    public boolean isMondschein(){
        return stunde < MONDSCHEINZEIT_ENDE || stunde > MONDSCHEINZEIT_ANFANG;
    }

    @Override
    public int compareTo(Zeitpunkt zp) {
        return this.compare(this, zp);
    }

    @Override
    public int compare(Zeitpunkt z1, Zeitpunkt z2) {
        return Integer.compare(z1.minutenSeitHeute(), z2.minutenSeitHeute());
    }

    public int minus(Zeitpunkt zp) {
        if (this.compareTo(zp) < 0) {
            throw new IllegalArgumentException("Differenz ist negativ");
        }
        return this.minutenSeitHeute() - zp.minutenSeitHeute();
    }
    public Zeitpunkt minus(int minuten) {
        return new Zeitpunkt(this.minutenSeitHeute() - minuten);
    }

    public Zeitpunkt plus(int minuten) {
        return new Zeitpunkt(this.minutenSeitHeute() + minuten);
    }
}
