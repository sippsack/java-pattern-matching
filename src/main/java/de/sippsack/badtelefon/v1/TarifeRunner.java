package de.sippsack.badtelefon.v1;

import de.sippsack.badtelefon.v1.tarif.Tarif;
import de.sippsack.badtelefon.v1.tarif.Type;

import java.util.Arrays;
import java.util.Random;

public class TarifeRunner {
	public static void main(String args[]) {
		Random random = new Random();

		for(Type tarif : Arrays.asList(Type.PRIVAT, Type.BUSINESS, Type.PROFI)) {
			System.out.println(String.format("\nVerarbeitung von Tarif %s", tarif));
			Kunde k = new Kunde(Tarif.of(tarif));
			
			for(int i = 0; i < 10; i++) {
				k.account(random.nextInt(5 + 1), new Zeitpunkt(random.nextInt(24), random.nextInt(60)));
			}
			
			System.out.println("Abrechnung: " + k.getGebuehr());
		}
	}
}
