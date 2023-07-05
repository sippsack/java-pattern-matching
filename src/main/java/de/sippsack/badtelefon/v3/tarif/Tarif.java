package de.sippsack.badtelefon.v3.tarif;

public sealed interface Tarif permits PrivatTarif, BusinessTarif, ProfiTarif {
}
