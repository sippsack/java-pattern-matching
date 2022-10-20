package de.sippsack.badtelefon.v2.tarif;

public record BusinessTarif(boolean vipKunde) implements Tarif {
    public static final double PREISPROMINUTE = 1.29;
    public static final double MONDSCHEINPREISPROMINUTE = 0.79;

    public BusinessTarif() {
        this(false);
    }

    public boolean isVipKunde() {
        return vipKunde;
    }

}
