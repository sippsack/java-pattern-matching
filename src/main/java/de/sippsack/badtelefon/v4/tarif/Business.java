package de.sippsack.badtelefon.v4.tarif;

public record Business(boolean keyAccountCustomer) implements Tarif {

    public Business() {
        this(false);
    }

    public static final double PREISPROMINUTE = 1.29;
    public static final double MONDSCHEINPREISPROMINUTE = 0.79;
}
