package de.sippsack.badtelefon.v31.tarif;

import de.sippsack.badtelefon.v31.Zeitpunkt;

public abstract class Tarif {

	public abstract void accept(TarifVisitor visitor, int minuten, Zeitpunkt zeitpunkt);

	public static Tarif of(Type type) {
		return switch(type) {
			case PRIVAT -> new PrivatTarif();
			case BUSINESS -> new BusinessTarif();
			case PROFI -> new ProfiTarif();
		};
	}
}
