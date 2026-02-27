package com.entity;

public class Capacity {

    private final boolean adsl;
    private final boolean fibre;

    public Capacity(boolean adsl, boolean fibre) {
        this.fibre = fibre;
        this.adsl = adsl;
    }

    public boolean hasAdslCapacity() {
        return adsl;
    }

    public boolean hasFibreCapacity() {
        return fibre;
    }

    @Override
    public String toString() {
        // return ToStringBuilder.reflectionToString(this);
       return "Capacity{" +
               "adsl=" + adsl +
               ", fibre=" + fibre +
               '}';
    }
}