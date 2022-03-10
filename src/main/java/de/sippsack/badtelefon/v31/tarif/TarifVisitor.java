package de.sippsack.badtelefon.v31.tarif;

import de.sippsack.badtelefon.v31.Zeitpunkt;

public interface TarifVisitor<TARIF extends Tarif> {
    void visit(TARIF tarif, int minuten, Zeitpunkt zeitpunkt);
}
