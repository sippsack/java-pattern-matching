package de.sippsack.badtelefon.v5;

import de.sippsack.badtelefon.v5.tarif.Business;
import de.sippsack.badtelefon.v5.tarif.Privat;
import de.sippsack.badtelefon.v5.tarif.Profi;
import de.sippsack.badtelefon.v5.tarif.Tarif;

public class Kunde {

    private double gesamtGebuehr;
    private final Tarif tarif;

    public Kunde(Tarif tarif) {
        this.tarif = tarif;
    }

    public void bucheGespraech(Gespraech gespraech) {
        gesamtGebuehr += switch(tarif) {
            case null -> 60; // bei leeren/ungültigen Tarif kostet jedes Gespräch pauschal 60,- ("Schwarztelefonieren")
            case Privat privat -> {
                var factor = (100 - privat.rabatt()) / 100;
                yield factor * privat.getNettoMinuten(gespraech.dauerInMinuten()) * (gespraech.start().isMondschein() ? privat.MONDSCHEINPREISPROMINUTE : privat.PREISPROMINUTE);
            }
            case Profi profi -> gespraech.dauerInMinuten() * profi.PREISPROMINUTE;
            case Business business -> {
                var factor = business.keyAccountCustomer() ? 0.8 : 1.0;
                yield factor * gespraech.dauerInMinuten() * (gespraech.start().isMondschein() ? business.MONDSCHEINPREISPROMINUTE : business.PREISPROMINUTE);
            }
        };
    }

    @Deprecated
    public void account(int minuten, int stunde, int minute) {
        this.bucheGespraech(new Gespraech(new Zeitpunkt(stunde, minute), minuten, tarif));
    }

    public double getGebuehr() {
        return gesamtGebuehr;
    }
}
