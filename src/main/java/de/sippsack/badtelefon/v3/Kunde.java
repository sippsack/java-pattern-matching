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
            case PrivatTarif privatTarif -> berechnePrivatAnruf(privatTarif, zeitpunkt, minuten);
            case BusinessTarif businessTarif && businessTarif.isVipKunde() -> berechneVIPAnruf(zeitpunkt, minuten);
            case BusinessTarif ignore -> berechneBusinessAnruf(zeitpunkt, minuten);
            case ProfiTarif ignore -> minuten * ProfiTarif.PREISPROMINUTE;
            case null -> 60;
        };
    }

    private double berechnePrivatAnruf(PrivatTarif privatTarif, Zeitpunkt zeitpunkt, int minuten) {
        var factor = (100 - privatTarif.rabatt()) / 100;
        var minutenPreis = zeitpunkt.isMondschein() ? PrivatTarif.MONDSCHEINPREISPROMINUTE : PrivatTarif.PREISPROMINUTE;
        var nettoMinuten = privatTarif.getNettoMinuten(minuten);
        return factor * nettoMinuten * minutenPreis;
    }

    private double berechneVIPAnruf(Zeitpunkt zeitpunkt, int minuten) {
        var minutenPreis = zeitpunkt.isMondschein() ? BusinessTarif.MONDSCHEINPREISPROMINUTE : BusinessTarif.PREISPROMINUTE;
        return 0.8 * minuten * minutenPreis;
    }

    private double berechneBusinessAnruf(Zeitpunkt zeitpunkt, int minuten) {
        var minutenPreis = zeitpunkt.isMondschein() ? BusinessTarif.MONDSCHEINPREISPROMINUTE : BusinessTarif.PREISPROMINUTE;
        return minuten * minutenPreis;
    }

    public double getGebuehr() {
        return gesamtGebuer;
    }
}
