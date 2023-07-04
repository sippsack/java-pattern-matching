package de.sippsack.badtelefon.v2.tarif;

import de.sippsack.badtelefon.v2.Zeitpunkt;

public interface TarifVisitor<T extends Tarif> {
    void visit(T tarif, int minuten, Zeitpunkt zeitpunkt);
}