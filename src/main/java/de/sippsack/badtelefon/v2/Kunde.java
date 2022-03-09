package de.sippsack.badtelefon.v2;

public class Kunde {
	private double gebuehr = 0.0;
	private Tarif tarif;


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
