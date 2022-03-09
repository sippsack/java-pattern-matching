package de.sippsack.badtelefon.v3.tarif;

import de.sippsack.badtelefon.v3.Zeitpunkt;

public interface TarifVisitor {
    void visit(PrivatTarif privatTarif, int minuten, Zeitpunkt zeitpunkt);
    void visit(BusinessTarif businessTarif, int minuten, Zeitpunkt zeitpunkt);
    void visit(ProfiTarif profiTarif, int minuten, Zeitpunkt zeitpunkt);
}
