package de.sippsack.badtelefon.v1;

import de.sippsack.badtelefon.v1.tarif.Tarif;

public class Kunde {
	private double gebuehr = 0.0;
	private final Tarif tarif;


	public Kunde(Tarif tarif) {
		this.tarif = tarif;
	}

	public void account(int minuten, Zeitpunkt zeitpunkt) {
		gebuehr += tarif.berechnePreis(minuten, zeitpunkt);
	}

	public double getGebuehr() {
		return gebuehr;
	}
}
