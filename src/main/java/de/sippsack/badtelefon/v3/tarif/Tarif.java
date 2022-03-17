package de.sippsack.badtelefon.v3.tarif;

import de.sippsack.badtelefon.v3.Zeitpunkt;

public abstract class Tarif {

	public final void accept(TarifVisitor<Tarif> visitor, int minuten, Zeitpunkt zeitpunkt) {
		visitor.visit(this, minuten, zeitpunkt);
	}
}
