package de.sippsack.badtelefon.v4;

import de.sippsack.badtelefon.v4.tarif.Business;
import de.sippsack.badtelefon.v4.tarif.Privat;
import de.sippsack.badtelefon.v4.tarif.Profi;
import de.sippsack.badtelefon.v4.tarif.Tarif;

public class Kunde {

    private double gesamtGebuehr;
    private final Tarif tarif;

    public Kunde(Tarif tarif) {
        this.tarif = tarif;
    }

    public void bucheGespraech(Zeitpunkt zeitpunkt, int dauer) {
        gesamtGebuehr += switch(tarif) {
            case null -> 60; // bei leeren/ungültigen Tarif kostet jedes Gespräch pauschal 60,- ("Schwarztelefonieren")
            case Privat privat -> {
                var factor = (100 - privat.rabatt()) / 100;
                yield factor * privat.getNettoMinuten(dauer) * (zeitpunkt.isMondschein() ? privat.MONDSCHEINPREISPROMINUTE : privat.PREISPROMINUTE);
            }
            case Profi profi -> dauer * profi.PREISPROMINUTE;
            case Business business -> {
                var factor = business.keyAccountCustomer() ? 0.8 : 1.0;
                yield factor * dauer * (zeitpunkt.isMondschein() ? business.MONDSCHEINPREISPROMINUTE : business.PREISPROMINUTE);
            }
        };
    }

    public void account(int minuten, int stunde, int minute) {
        this.bucheGespraech(new Zeitpunkt(stunde, minute), minuten);
    }

    public double getGebuehr() {
        return gesamtGebuehr;
    }
}
