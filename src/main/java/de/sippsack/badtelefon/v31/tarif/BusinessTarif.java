package de.sippsack.badtelefon.v31.tarif;

public non-sealed class BusinessTarif implements Tarif {
    public static final double PREISPROMINUTE = 1.29;
    public static final double MONDSCHEINPREISPROMINUTE = 0.79;

    private final boolean vipKunde;
    private int anzahlTelefonate;


    public BusinessTarif() {
        this(false);
    }

    public BusinessTarif(boolean vipKunde) {
        this.vipKunde = vipKunde;
        anzahlTelefonate = 0;
    }

    public boolean isVipKunde() {
        return vipKunde;
    }

    public double dynamischerFaktor() {
        anzahlTelefonate += 1;
        return 1 / anzahlTelefonate;
    }
}
