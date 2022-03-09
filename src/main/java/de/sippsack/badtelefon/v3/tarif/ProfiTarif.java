package de.sippsack.badtelefon.v3.tarif;

import de.sippsack.badtelefon.v3.Zeitpunkt;

public class ProfiTarif extends Tarif {

    @Override
    public void accept(TarifVisitor visitor, int minuten, Zeitpunkt zeitpunkt) {
        visitor.visit(this, minuten, zeitpunkt);
    }
}
