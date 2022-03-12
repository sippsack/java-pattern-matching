package de.sippsack.badtelefon.v31;

import de.sippsack.badtelefon.v31.tarif.BusinessTarif;
import de.sippsack.badtelefon.v31.tarif.PrivatTarif;
import de.sippsack.badtelefon.v31.tarif.ProfiTarif;
import de.sippsack.badtelefon.v31.tarif.Tarif;

public class Kunde {
    private final Tarif tarif;
    private double gesamtPreis;

    public Kunde(Tarif tarif) {
        this.tarif = tarif;
    }

    public void account(int minuten, Zeitpunkt zeitpunkt) {
        bucheGespraech(zeitpunkt, minuten);
    }

    public void bucheGespraech(Zeitpunkt zeitpunkt, int dauer) {
        gesamtPreis += switch (tarif) {
            case PrivatTarif privatTarif -> {
                var factor = (100 - privatTarif.getRabatt()) / 100;
                var minutenPreis = zeitpunkt.isMondschein() ? PrivatTarif.MONDSCHEINPREISPROMINUTE : PrivatTarif.PREISPROMINUTE;
                var nettoMinuten = privatTarif.getNettoMinuten(dauer);
                yield factor * nettoMinuten * minutenPreis;
            }
            case BusinessTarif businessTarif -> {
                var factor = businessTarif.isVipKunde() ? 0.8 : 1.0;
                double minutenPreis = zeitpunkt.isMondschein() ? BusinessTarif.MONDSCHEINPREISPROMINUTE : BusinessTarif.PREISPROMINUTE;
                yield factor * dauer * minutenPreis;
            }
            case ProfiTarif ignore -> dauer * ProfiTarif.PREISPROMINUTE;
            case null, default -> 60;
        };
    }

    public double getGebuehr() {
        return gesamtPreis;
    }
}
