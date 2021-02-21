package com.example.nutriscore.calculation;

import java.util.List;

public class Food {
    private final int energie;
    private final double zucker;
    private final double gesFettsaeuren;
    private final double natrium;
    private final int fruechteGemuese;
    private final double ballaststoffe;
    private final double eiweiss;

    public Food(int energie, double zucker, double gesFettsaeuren, double natrium, int fruechteGemuese, double ballaststoffe, double eiweiss) {
        this.energie = energie;
        this.zucker = zucker;
        this.gesFettsaeuren = gesFettsaeuren;
        this.natrium = natrium;
        this.fruechteGemuese = fruechteGemuese;
        this.ballaststoffe = ballaststoffe;
        this.eiweiss = eiweiss;
    }

    public Food(List<Double> doubles){
        this(doubles.get(0).intValue(), doubles.get(1), doubles.get(2), doubles.get(3), doubles.get(4).intValue(), doubles.get(5), doubles.get(6));
    }

    public Food(double zucker) {
        this(0, zucker, 0, 0, 0, 0, 0);
    }

    public int getEnergie() {
        return energie;
    }

    public double getZucker() {
        return zucker;
    }

    public double getGesFettsaeuren() {
        return gesFettsaeuren;
    }

    public double getNatrium() {
        return natrium;
    }

    public int getFruechteGemuese() {
        return fruechteGemuese;
    }

    public double getBallaststoffe() {
        return ballaststoffe;
    }

    public double getEiweiss() {
        return eiweiss;
    }
}
