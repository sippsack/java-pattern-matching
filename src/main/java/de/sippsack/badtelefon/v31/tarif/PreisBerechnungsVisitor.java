package de.sippsack.badtelefon.v31.tarif;

import de.sippsack.badtelefon.v31.Zeitpunkt;

public class PreisBerechnungsVisitor<T extends Tarif> implements TarifVisitor<T> {
    private double gebuehr = 0.0;

    public double getGebuehr() {
        return gebuehr;
    }

    @Override
    public void visit(T tarif, int minuten, Zeitpunkt zeitpunkt) {
        gebuehr += switch(tarif) {
            case PrivatTarif privat -> {
                var nettoMinuten = Math.max(minuten - 1, 0);
                yield nettoMinuten * (zeitpunkt.isMondschein() ? PrivatTarif.MONDSCHEINPREIS : 1.99);
            }
            case BusinessTarif business -> minuten * (zeitpunkt.isMondschein() ? 0.79 : 1.29);
            case ProfiTarif profi -> minuten * 0.69;
            default -> 60; // bei unbekannten/ungültigen Tarif kostet das Gespräch pauschal 60 Euro (Schwarztelefonieren)
        };
    }
}
