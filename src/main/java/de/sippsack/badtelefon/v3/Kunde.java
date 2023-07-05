package de.sippsack.badtelefon.v3;

import de.sippsack.badtelefon.v3.tarif.BusinessTarif;
import de.sippsack.badtelefon.v3.tarif.PrivatTarif;
import de.sippsack.badtelefon.v3.tarif.ProfiTarif;
import de.sippsack.badtelefon.v3.tarif.Tarif;

public class Kunde {
    private final Tarif tarif;

    private double gebuehr;

    public Kunde(Tarif tarif) {
        this.tarif = tarif;
    }

    public void account(int minuten, Zeitpunkt zeitpunkt) {
        gebuehr += switch(tarif) {
            case null -> 60; // bei leeren/ungültigen Tarif kostet jedes Gespräch pauschal 60,- ("Schwarztelefonieren")
            case ProfiTarif _ -> minuten * ProfiTarif.PREISPROMINUTE;
            case PrivatTarif(var rabatt) -> {
                var factor = (100 - rabatt) / 100;
                yield factor * PrivatTarif.getNettoMinuten(minuten) * (zeitpunkt.isMondschein() ? PrivatTarif.MONDSCHEINPREISPROMINUTE : PrivatTarif.PREISPROMINUTE);
            }
            case BusinessTarif(boolean vipKunde) when vipKunde -> 0.8 * minuten * (zeitpunkt.isMondschein() ? BusinessTarif.MONDSCHEINPREISPROMINUTE : BusinessTarif.PREISPROMINUTE);
            case BusinessTarif _ -> minuten * (zeitpunkt.isMondschein() ? BusinessTarif.MONDSCHEINPREISPROMINUTE : BusinessTarif.PREISPROMINUTE);
        };
    }

    public double getGebuehr() {
        return gebuehr;
    }
}
