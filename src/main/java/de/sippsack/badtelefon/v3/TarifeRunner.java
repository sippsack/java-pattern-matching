package de.sippsack.badtelefon.v3;

import de.sippsack.badtelefon.v3.tarif.*;

import java.util.Arrays;
import java.util.Random;

public class TarifeRunner {
	public static void main(String args[]) {
		Random random = new Random();

		for(Tarif tarif : Arrays.asList(new PrivatTarif(), new BusinessTarif(), new ProfiTarif())) {
			System.out.println(String.format("\nVerarbeitung von Tarif %s", tarif));
			Kunde k = new Kunde(tarif);
			
			for(int i = 0; i < 10; i++) {
				k.account(random.nextInt(5 + 1), new Zeitpunkt(random.nextInt(24), random.nextInt(60)));
			}
			
			System.out.println("Abrechnung: " + k.getGebuehr());
		}
	}
}
