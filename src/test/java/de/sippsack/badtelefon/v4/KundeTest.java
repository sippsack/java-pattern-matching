package de.sippsack.badtelefon.v4;

import de.sippsack.badtelefon.v4.Kunde;
import de.sippsack.badtelefon.v4.tarif.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KundeTest {

    Kunde privat = new Kunde(new Privat(1.99, 0.69));
    Kunde business = new Kunde(new Business(1.29, 0.79));
    Kunde profi = new Kunde(new Profi(0.69));

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