package de.sippsack.badtelefon.v3;

import de.sippsack.badtelefon.v3.tarif.PreisBerechnungsVisitor;
import de.sippsack.badtelefon.v3.tarif.Tarif;

public class Kunde {
	private Tarif tarif;
	private final PreisBerechnungsVisitor visitor = new PreisBerechnungsVisitor();


	public Kunde(Tarif tarif) {
		this.tarif = tarif;
	}

	public void account(int minuten, Zeitpunkt zeitpunkt) {
		tarif.accept(visitor, minuten, zeitpunkt);
	}

	public double getGebuehr() {
		return visitor.getGebuehr();
	}
}
