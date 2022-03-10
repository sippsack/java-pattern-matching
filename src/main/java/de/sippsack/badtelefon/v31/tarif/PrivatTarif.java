package de.sippsack.badtelefon.v31.tarif;

import de.sippsack.badtelefon.v31.Zeitpunkt;

public class PrivatTarif extends Tarif {

    public final static double MONDSCHEINPREIS = 0.69;


    @Override
    public void accept(TarifVisitor visitor, int minuten, Zeitpunkt zeitpunkt) {
        visitor.visit(this, minuten, zeitpunkt);
    }
}
