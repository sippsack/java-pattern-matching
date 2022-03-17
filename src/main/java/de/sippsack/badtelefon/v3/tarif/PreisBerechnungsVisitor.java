package de.sippsack.badtelefon.v3.tarif;

import de.sippsack.badtelefon.v3.Zeitpunkt;

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
            case BusinessTarif businessTarif && businessTarif.isVipKunde() -> {
                var minutenPreis = zeitpunkt.isMondschein() ? BusinessTarif.MONDSCHEINPREISPROMINUTE : BusinessTarif.PREISPROMINUTE;
                yield 0.8 * minuten * minutenPreis;
            }
            case BusinessTarif ignore -> {
                var minutenPreis = zeitpunkt.isMondschein() ? BusinessTarif.MONDSCHEINPREISPROMINUTE : BusinessTarif.PREISPROMINUTE;
                yield minuten * BusinessTarif.MONDSCHEINPREISPROMINUTE;
            }
            case ProfiTarif ignore -> minuten * ProfiTarif.PREISPROMINUTE;
            case null, default -> 60;
        };
    }
}
