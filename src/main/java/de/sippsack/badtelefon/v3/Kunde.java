package de.sippsack.badtelefon.v3;

import de.sippsack.badtelefon.v3.tarif.BusinessTarif;
import de.sippsack.badtelefon.v3.tarif.PrivatTarif;
import de.sippsack.badtelefon.v3.tarif.ProfiTarif;
import de.sippsack.badtelefon.v3.tarif.Tarif;

public class Kunde {
    private final Tarif tarif;
    private double gesamtGebuer;

    public Kunde(Tarif tarif) {
        this.tarif = tarif;
        gesamtGebuer = 0;
    }

    public void account(int minuten, Zeitpunkt zeitpunkt) {
        gesamtGebuer += switch (tarif) {
            case PrivatTarif privatTarif -> {
                var factor = (100 - privatTarif.rabatt()) / 100;
                var minutenPreis = zeitpunkt.isMondschein() ? PrivatTarif.MONDSCHEINPREISPROMINUTE : PrivatTarif.PREISPROMINUTE;
                var nettoMinuten = privatTarif.getNettoMinuten(minuten);
                yield factor * nettoMinuten * minutenPreis;
            }
            case BusinessTarif businessTarif && businessTarif.isVipKunde() -> {
                var minutenPreis = zeitpunkt.isMondschein() ? BusinessTarif.MONDSCHEINPREISPROMINUTE : BusinessTarif.PREISPROMINUTE;
                yield 0.8 * minuten * minutenPreis;
            }
            case BusinessTarif ignore -> {
                var minutenPreis = zeitpunkt.isMondschein() ? BusinessTarif.MONDSCHEINPREISPROMINUTE : BusinessTarif.PREISPROMINUTE;
                yield minuten * minutenPreis;
            }
            case ProfiTarif ignore -> minuten * ProfiTarif.PREISPROMINUTE;
            case null -> 60;
        };
    }

    public double getGebuehr() {
        return gesamtGebuer;
    }
}
