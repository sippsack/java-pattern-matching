package de.sippsack.badtelefon.v31.tarif;

import de.sippsack.badtelefon.v31.Zeitpunkt;

public class BusinessTarif extends Tarif {

    @Override
    public void accept(TarifVisitor visitor, int minuten, Zeitpunkt zeitpunkt) {
        visitor.visit(this, minuten, zeitpunkt);
    }
}
