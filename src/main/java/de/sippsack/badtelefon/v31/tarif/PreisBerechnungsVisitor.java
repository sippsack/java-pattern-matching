package de.sippsack.badtelefon.v31.tarif;

import de.sippsack.badtelefon.v31.Zeitpunkt;

public class PreisBerechnungsVisitor<T extends Tarif> implements TarifVisitor<T> {
    private double gebuehr = 0.0;

    public double getGebuehr() {
        return gebuehr;
    }

    @Override
    public void visit(T tarif, int minuten, Zeitpunkt zeitpunkt) {
        gebuehr += switch (tarif) {
            case PrivatTarif privatTarif -> {
                var factor = (100 - privatTarif.getRabatt()) / 100;
                var minutenPreis = zeitpunkt.isMondschein() ? PrivatTarif.MONDSCHEINPREISPROMINUTE : PrivatTarif.PREISPROMINUTE;
                var nettoMinuten = privatTarif.getNettoMinuten(minuten);
                yield factor * nettoMinuten * minutenPreis;
            }
            case BusinessTarif businessTarif -> {
                var factor = businessTarif.isVipKunde() ? 0.8 : 1.0;
                double minutenPreis = zeitpunkt.isMondschein() ? BusinessTarif.MONDSCHEINPREISPROMINUTE : BusinessTarif.PREISPROMINUTE;
                yield factor * minuten * minutenPreis;
            }
            case ProfiTarif ignore -> minuten * ProfiTarif.PREISPROMINUTE;
            case null, default -> 60;
        };
    }
}
