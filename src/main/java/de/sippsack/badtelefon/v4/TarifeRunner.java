package de.sippsack.badtelefon.v4;

import de.sippsack.badtelefon.v4.tarif.Business;
import de.sippsack.badtelefon.v4.tarif.Privat;
import de.sippsack.badtelefon.v4.tarif.Profi;
import de.sippsack.badtelefon.v4.tarif.Tarif;

import java.util.Arrays;
import java.util.Random;

public class TarifeRunner {
	public static void main(String args[]) {
		Random random = new Random();

		for(Tarif tarif : Arrays.asList(new Privat(0.69, 1.99), new Business(0.79, 1.29), new Profi(0.69))) {
			System.out.println(String.format("\nVerarbeitung von Tarif %s", tarif));
			Kunde k = new Kunde(null);
			
			for(int i = 0; i < 10; i++) {
				var startZeit = new Zeitpunkt(random.nextInt(24), random.nextInt(60));
				var gespraech = new Gespraech(startZeit, random.nextInt(5 + 1), tarif);
				k.bucheGespraech(gespraech);
			}
			
			System.out.println("Abrechnung: " + k.getGebuehr());
		}
	}
}
