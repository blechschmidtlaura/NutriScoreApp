package com.example.nutriscore.calculation;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class Product implements Parcelable {

    private int energie;
    private double zucker;
    private double gesFettsaeuren;
    private double natrium;
    private int fruechteGemuese;
    private double ballaststoffe;
    private double eiweiss;

    private  ScoreTabelle energieScore;
    private  ScoreTabelle zuckerScore;
    private  ScoreTabelle gesFettsaeurenScore;
    private  ScoreTabelle natriumScore;
    private  ScoreTabelle fruechteGemueseScore;
    private  ScoreTabelle ballaststoffeScore;
    private  ScoreTabelle eiweissScore;

    public void setEnergie(int energie) {
        this.energie = energie;
    }

    public void setZucker(double zucker) {
        this.zucker = zucker;
    }

    public void setGesFettsaeuren(double gesFettsaeuren) {
        this.gesFettsaeuren = gesFettsaeuren;
    }

    public void setNatrium(double natrium) {
        this.natrium = natrium;
    }

    public void setFruechteGemuese(int fruechteGemuese) {
        this.fruechteGemuese = fruechteGemuese;
    }

    public void setBallaststoffe(double ballaststoffe) {
        this.ballaststoffe = ballaststoffe;
    }

    public void setEiweiss(double eiweiss) {
        this.eiweiss = eiweiss;
    }

    public ScoreTabelle getEnergieScore() {
        return energieScore;
    }

    public void setEnergieScore(ScoreTabelle energieScore) {
        this.energieScore = energieScore;
    }

    public ScoreTabelle getZuckerScore() {
        return zuckerScore;
    }

    public void setZuckerScore(ScoreTabelle zuckerScore) {
        this.zuckerScore = zuckerScore;
    }

    public ScoreTabelle getGesFettsaeurenScore() {
        return gesFettsaeurenScore;
    }

    public void setGesFettsaeurenScore(ScoreTabelle gesFettsaeurenScore) {
        this.gesFettsaeurenScore = gesFettsaeurenScore;
    }

    public ScoreTabelle getNatriumScore() {
        return natriumScore;
    }

    public void setNatriumScore(ScoreTabelle natriumScore) {
        this.natriumScore = natriumScore;
    }

    public ScoreTabelle getFruechteGemueseScore() {
        return fruechteGemueseScore;
    }

    public void setFruechteGemueseScore(ScoreTabelle fruechteGemueseScore) {
        this.fruechteGemueseScore = fruechteGemueseScore;
    }

    public ScoreTabelle getBallaststoffeScore() {
        return ballaststoffeScore;
    }

    public void setBallaststoffeScore(ScoreTabelle ballaststoffeScore) {
        this.ballaststoffeScore = ballaststoffeScore;
    }

    public ScoreTabelle getEiweissScore() {
        return eiweissScore;
    }

    public void setEiweissScore(ScoreTabelle eiweissScore) {
        this.eiweissScore = eiweissScore;
    }

    /**
     *
     * @param energie die Kalorien
     * @param zucker Zucker des Produkts
     * @param gesFettsaeuren Gesättigte Fettsäuren des Produkts
     * @param natrium Natrium also Salz des Produktes
     * @param fruechteGemuese Menge an fruechtengemuesen und nüssen
     * @param ballaststoffe Anteil an Balaststoffen
     * @param eiweiss Eiweiß Anteil
     */
    public Product(int energie, double zucker, double gesFettsaeuren, double natrium, int fruechteGemuese, double ballaststoffe, double eiweiss) {
        this.initializeValues(energie, zucker, gesFettsaeuren, natrium, fruechteGemuese, ballaststoffe, eiweiss);
    }

    /**
     * Parcelable Konstruktor damit das Food Object von einer Activity an die nächste weitergegebene werden kann
     * @param in  Das übergebene Parcel Object
     */
    private Product(Parcel in) {
        List<Double> myList = new ArrayList<>();
        in.readList(myList,List.class.getClassLoader());
        initializeValues(myList);
    }

    /**
     * An diesen Konstruktor kann eine Liste der Inhaltsstoffe übergeben werden,
     * diese wird dann einfach den entsprechenden Attributen zugeordnet.
     * 1. Element Energie
     * 2. Zucker ...
     * @param doubles
     */
    public Product(List<Double> doubles){
        initializeValues(doubles);
    }

    public Product(double zucker) {
        this(0, zucker, 0, 0, 0, 0, 0);
    }

    /**
     * Initialisiert die Nahrungswerte
     * @param energie Kalorien
     * @param zucker Zucker
     * @param gesFettsaeuren Fettsäuren
     * @param natrium Salz
     * @param fruechteGemuese Menge fruechte oder gemuese oder nüsse
     * @param ballaststoffe Ballaststoffe
     * @param eiweiss Eiweiß
     */
    private void initializeValues(int energie, double zucker, double gesFettsaeuren, double natrium, int fruechteGemuese, double ballaststoffe, double eiweiss){
        this.energie = energie;
        this.zucker = zucker;
        this.gesFettsaeuren = gesFettsaeuren;
        this.natrium = natrium;
        this.fruechteGemuese = fruechteGemuese;
        this.ballaststoffe = ballaststoffe;
        this.eiweiss = eiweiss;
    }

    /**
     * Initialisiert die Werte des Foods Objekt mit einer Liste
     * @param doubles  Liste der Nahrungswerte
     */
    private void initializeValues(List<Double> doubles){
        initializeValues(doubles.get(0).intValue(), doubles.get(1), doubles.get(2), doubles.get(3), doubles.get(4).intValue(), doubles.get(5), doubles.get(6));
    }

    /**
     * Getter des Energie Attributs
     * @return  Energie
     */
    public int getEnergie() {
        return energie;
    }

    /**
     * Getter des Zucker Attributs
     * @return Zucker
     */
    public double getZucker() {
        return zucker;
    }

    /**
     * Getter Fettsäuren
     * @return Fettsäuren
     */
    public double getGesFettsaeuren() {
        return gesFettsaeuren;
    }

    /**
     * Getter Salz
     * @return  Salz
     */
    public double getNatrium() {
        return natrium;
    }

    /**
     * Getter Früchte und Gemüse
     * @return Früchte und Gemüse
     */
    public int getFruechteGemuese() {
        return fruechteGemuese;
    }

    /**
     * Getter Ballaststoffe
     * @return  Balaststoffe
     */
    public double getBallaststoffe() {
        return ballaststoffe;
    }


    /**
     * Getter Eiweiß
     * @return  Eiweiß
     */
    public double getEiweiss() {
        return eiweiss;
    }

    /**
     * Überschreibt Parceable Method
     * @return  0
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Überschreibt Parceable Methode
     * @param out Parcel Objekt
     * @param flags 0
     */
    @Override
    @RequiresApi(api = Build.VERSION_CODES.R)
    public void writeToParcel(Parcel out, int flags) {
        out.writeList(List.of((double)energie, (double)zucker, (double)gesFettsaeuren, (double)natrium, (double)fruechteGemuese, (double)ballaststoffe, (double)eiweiss));
    }

    public static final Parcelable.Creator<Product> CREATOR
            = new Parcelable.Creator<Product>() {
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

}
