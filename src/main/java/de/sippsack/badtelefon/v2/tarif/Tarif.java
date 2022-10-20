package de.sippsack.badtelefon.v2.tarif;

public sealed interface Tarif permits PrivatTarif, BusinessTarif, ProfiTarif {
}
