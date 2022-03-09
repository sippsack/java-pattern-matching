package de.sippsack.badtelefon.v2;

public enum Tarif {
    PRIVAT {
        protected double getMinutenPreis(Zeitpunkt zeitpunkt) {
            return zeitpunkt.isMondschein() ? 0.69 : 1.99;
        }
        protected int getNettoMinuten(int minuten) {
            return Math.max(minuten - 1, 0);
        }
    }, BUSINESS {
        @Override
        protected double getMinutenPreis(Zeitpunkt zeitpunkt) {
            return zeitpunkt.isMondschein() ? 0.79 : 1.29;
        }
    },
    PROFI {
        protected double getMinutenPreis(Zeitpunkt zeitpunkt) {
            return 0.69;
        }
    };

    public final double berechnePreis(int minuten, Zeitpunkt zeitpunkt) {
        return getNettoMinuten(minuten) * getMinutenPreis(zeitpunkt);
    }

    protected abstract double getMinutenPreis(Zeitpunkt zeitpunkt);

    protected int getNettoMinuten(int minuten) {
        return minuten;
    }

}
