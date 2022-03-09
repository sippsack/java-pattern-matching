package de.sippsack.badtelefon.v4;

import de.sippsack.badtelefon.v4.tarif.Tarif;

public record Gespraech(Zeitpunkt start, Zeitpunkt ende, Tarif tarif) {
    public Gespraech {
        if (start.compareTo(ende) > 0) {
            throw new IllegalArgumentException(String.format("Startzeit darf nicht nach Endzeit liegen: %s >= %s", start, ende));
        }
    }

    public Gespraech(Zeitpunkt start, int dauer, Tarif tarif) {
        this(start, start.plus(dauer), tarif);
    }

    public int dauerInMinuten() {
        return ende.minus(start);
    }
}
