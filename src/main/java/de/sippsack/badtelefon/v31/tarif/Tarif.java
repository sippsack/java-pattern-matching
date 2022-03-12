package de.sippsack.badtelefon.v31.tarif;

import de.sippsack.badtelefon.v31.Zeitpunkt;

public abstract class Tarif {

	public final void accept(TarifVisitor<Tarif> visitor, int minuten, Zeitpunkt zeitpunkt) {
		visitor.visit(this, minuten, zeitpunkt);
	}
}
