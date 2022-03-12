package de.sippsack.badtelefon.v31.tarif;

import de.sippsack.badtelefon.v31.Zeitpunkt;

public interface TarifVisitor<T extends Tarif> {
    void visit(T tarif, int minuten, Zeitpunkt zeitpunkt);
}
