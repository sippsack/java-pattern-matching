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

    public void bucheGespraech(Gespraech gespraech) {
        gesamtGebuehr += switch(tarif) {
            case null -> 60; // bei leeren/ungültigen Tarif kostet jedes Gespräch pauschal 60,- ("Schwarztelefonieren")
            case Privat privat -> privat.getNettoMinuten(gespraech.dauerInMinuten()) * (gespraech.start().isMondschein() ? privat.mondscheinPreisProMinute() : privat.preisProMinute());
            case Profi profi -> gespraech.dauerInMinuten() * profi.preisProMinute();
            case Business business -> gespraech.dauerInMinuten() * (gespraech.start().isMondschein() ? business.mondscheinPreisProMinute() : business.preisProMinute());
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
