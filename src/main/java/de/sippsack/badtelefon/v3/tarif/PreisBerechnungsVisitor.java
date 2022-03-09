package de.sippsack.badtelefon.v3.tarif;

import de.sippsack.badtelefon.v3.Zeitpunkt;

public class PreisBerechnungsVisitor implements TarifVisitor {
    private double gebuehr = 0.0;

    @Override
    public void visit(PrivatTarif privatTarif, int minuten, Zeitpunkt zeitpunkt) {
        var nettoMinuten = Math.max(minuten - 1, 0);
        gebuehr += nettoMinuten * (zeitpunkt.isMondschein() ? 0.69 : 1.99);
    }

    @Override
    public void visit(BusinessTarif businessTarif, int minuten, Zeitpunkt zeitpunkt) {
        gebuehr += minuten * (zeitpunkt.isMondschein() ? 0.79 : 1.29);
    }

    @Override
    public void visit(ProfiTarif profiTarif, int minuten, Zeitpunkt zeitpunkt) {
        gebuehr += minuten * 0.69;
    }

    public double getGebuehr() {
        return gebuehr;
    }
}
