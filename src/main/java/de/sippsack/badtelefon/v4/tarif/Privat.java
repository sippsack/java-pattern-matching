package de.sippsack.badtelefon.v4.tarif;

public record Privat(double preisProMinute, double mondscheinPreisProMinute) implements Tarif {
    public int getNettoMinuten(int minuten) {
        return Math.max(minuten - 1, 0);
    }
}
