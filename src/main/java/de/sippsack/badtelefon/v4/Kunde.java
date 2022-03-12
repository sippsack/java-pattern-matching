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
        gesamtGebuehr += switch (tarif) {
            case null -> 60; // bei leeren/ungültigen Tarif kostet jedes Gespräch pauschal 60,- ("Schwarztelefonieren")
            case Privat privat -> {
                var factor = (100 - privat.rabatt()) / 100;
                var nettoMinuten = privat.getNettoMinuten(dauer);
                var minutenPreis = zeitpunkt.isMondschein() ? Privat.MONDSCHEINPREISPROMINUTE : Privat.PREISPROMINUTE;
                yield factor * nettoMinuten * minutenPreis;
            }
            case Profi profi -> dauer * Profi.PREISPROMINUTE;
            case Business business -> {
                var factor = business.keyAccountCustomer() ? 0.8 : 1.0;
                var minutenPreis = zeitpunkt.isMondschein() ? Business.MONDSCHEINPREISPROMINUTE : Business.PREISPROMINUTE;
                yield factor * dauer * minutenPreis;
            }
        };
    }

    public void account(int minuten, int stunde, int minute) {
        bucheGespraech(new Zeitpunkt(stunde, minute), minuten);
    }

    public double getGebuehr() {
        return gesamtGebuehr;
    }
}
