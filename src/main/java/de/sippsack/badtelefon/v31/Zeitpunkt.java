package de.sippsack.badtelefon.v31;

public record Zeitpunkt(int stunde, int minute) {
    public static final int MONDSCHEINZEIT_ENDE = 9;
    public static final int MONDSCHEINZEIT_ANFANG = 18;

    public boolean isMondschein() {
        return stunde < Zeitpunkt.MONDSCHEINZEIT_ENDE || stunde > Zeitpunkt.MONDSCHEINZEIT_ANFANG;
    }
}