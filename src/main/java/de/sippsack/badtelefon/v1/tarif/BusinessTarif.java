package de.sippsack.badtelefon.v1.tarif;

import de.sippsack.badtelefon.v1.Zeitpunkt;

public class BusinessTarif extends Tarif {

    @Override
    protected double getMinutenPreis(Zeitpunkt zeitpunkt) {
        return zeitpunkt.isMondschein() ? 0.79 : 1.29;
    }

}
