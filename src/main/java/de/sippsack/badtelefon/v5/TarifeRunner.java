package de.sippsack.badtelefon.v5;

import de.sippsack.badtelefon.v5.tarif.Business;
import de.sippsack.badtelefon.v5.tarif.Privat;
import de.sippsack.badtelefon.v5.tarif.Profi;
import de.sippsack.badtelefon.v5.tarif.Tarif;

import java.util.Arrays;
import java.util.Random;

public class TarifeRunner {
	public static void main(String args[]) {
		Random random = new Random();

		for(Tarif tarif : Arrays.asList(new Privat(), new Business(false), new Profi())) {
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
