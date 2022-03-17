package de.sippsack.badtelefon.v3.tarif;

import de.sippsack.badtelefon.v3.Zeitpunkt;

public interface TarifVisitor<T extends Tarif> {
    void visit(T tarif, int minuten, Zeitpunkt zeitpunkt);
}
