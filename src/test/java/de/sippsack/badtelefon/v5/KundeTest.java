package de.sippsack.badtelefon.v5;

import de.sippsack.badtelefon.v5.Kunde;
import de.sippsack.badtelefon.v5.tarif.Business;
import de.sippsack.badtelefon.v5.tarif.Privat;
import de.sippsack.badtelefon.v5.tarif.Profi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KundeTest {

    Kunde privat = new Kunde(new Privat());
    Kunde business = new Kunde(new Business());
    Kunde profi = new Kunde(new Profi());

    @Test
    void privatTarif() {
        privat.account(17, 10, 30);
        privat.account(5, 2, 37);
        privat.account(37, 22, 05);
        assertEquals(59.44, privat.getGebuehr());
    }

    @Test
    void businessTarif() {
        business.account(17, 10, 30);
        business.account(5, 2, 37);
        business.account(37, 22, 05);
        assertEquals(55.11, business.getGebuehr());
    }

    @Test
    void profiTarif() {
        profi.account(17, 10, 30);
        profi.account(37, 22, 05);
        assertEquals(37.26, profi.getGebuehr(), 0.01);
    }
}