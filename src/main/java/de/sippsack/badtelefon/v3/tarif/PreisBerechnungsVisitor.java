package de.sippsack.badtelefon.v3.tarif;

import de.sippsack.badtelefon.v3.Zeitpunkt;

public class PreisBerechnungsVisitor<T extends Tarif> implements TarifVisitor<T> {
    private double gebuehr = 0.0;

    public double getGebuehr() {
        return gebuehr;
    }

    @Override
    public void visit(T tarif, int minuten, Zeitpunkt zeitpunkt) {
        switch (tarif) {
            case PrivatTarif privatTarif: {
                var factor = (100 - privatTarif.getRabatt()) / 100;
                var minutenPreis = zeitpunkt.isMondschein() ? PrivatTarif.MONDSCHEINPREISPROMINUTE : PrivatTarif.PREISPROMINUTE;
                var nettoMinuten = privatTarif.getNettoMinuten(minuten);
                gebuehr += factor * nettoMinuten * minutenPreis;
                break;
            }
            case BusinessTarif businessTarif: {
                var factor = businessTarif.isVipKunde() ? 0.8 : 1.0;
                var minutenPreis = zeitpunkt.isMondschein() ? BusinessTarif.MONDSCHEINPREISPROMINUTE : BusinessTarif.PREISPROMINUTE;
                gebuehr += factor * minuten * minutenPreis;
                break;
            }
            case ProfiTarif ignore:
                gebuehr += minuten * ProfiTarif.PREISPROMINUTE;
                break;
            case null, default:
                gebuehr += 60;
        }
    }
}
