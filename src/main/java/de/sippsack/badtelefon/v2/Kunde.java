package de.sippsack.badtelefon.v2;

import de.sippsack.badtelefon.v2.tarif.*;

public class Kunde {
    private final Tarif tarif;

    public Kunde(Tarif tarif) {
        this.tarif = tarif;
    }

    private double gebuehr = 0.0;

    public double getGebuehr() {
        return gebuehr;
    }

    public void account(int minuten, Zeitpunkt zeitpunkt) {
        gebuehr += switch(tarif) {
            case PrivatTarif(double rabatt) privatTarif -> {
                var factor = (100 - privatTarif.getRabatt()) / 100;
                var minutenPreis = zeitpunkt.isMondschein() ? PrivatTarif.MONDSCHEINPREISPROMINUTE : PrivatTarif.PREISPROMINUTE;
                var nettoMinuten = privatTarif.getNettoMinuten(minuten);
                yield factor * nettoMinuten * minutenPreis;
            }
            case ProfiTarif profiTarif -> {
                yield minuten * ProfiTarif.PREISPROMINUTE;
            }
            case BusinessTarif businessTarif -> {
                var factor = businessTarif.isVipKunde() ? 0.8 : 1.0;
                double minutenPreis = zeitpunkt.isMondschein() ? BusinessTarif.MONDSCHEINPREISPROMINUTE : BusinessTarif.PREISPROMINUTE;
                yield factor * minuten * minutenPreis;
            }
            //case null -> throw new NullPointerException();
            //case default, null ->
            //        throw new IllegalStateException("Unexpected value: " + tarif);
        };
    }
}
