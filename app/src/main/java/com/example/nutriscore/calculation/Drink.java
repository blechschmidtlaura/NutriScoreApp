package com.example.nutriscore.calculation;

import android.content.Context;

import com.example.nutriscore.FileManager;

import java.io.IOException;

public class Drink extends Product{

    public Drink(Context c, int energie, double zucker, double gesFettsaeuren, double natrium, int fruechteGemuese, double ballaststoffe, double eiweiss) {
        super(energie, zucker, gesFettsaeuren, natrium, fruechteGemuese, ballaststoffe, eiweiss);
        loadFiles(c);
    }

    /**
     * Die ScoreTabellen für berechnung des NutriScores werden aus dem Lokalen Speicher ausgelesen.
     * @param c Context wird benötigt um auf den Lokalen Speicher des Handys zuzugreifen in welchem die Tabellen gespeichert sind
     */

    private void loadFiles(Context c){
        try {
            this.setEnergieScore(new ScoreTabelle(FileManager.getInputStreamFile("Energie_Drink.txt", c)));
            this.setZuckerScore(new ScoreTabelle(FileManager.getInputStreamFile("Zucker_Drink.txt", c)));
            this.setGesFettsaeurenScore(new ScoreTabelle(FileManager.getInputStreamFile("GesFettsaeuren_Drink.txt", c)));
            this.setNatriumScore(new ScoreTabelle(FileManager.getInputStreamFile("Natrium_Drink.txt", c)));
            this.setFruechteGemueseScore(new ScoreTabelle(FileManager.getInputStreamFile("FruechteGemuese_Drink.txt", c)));
            this.setBallaststoffeScore(new ScoreTabelle(FileManager.getInputStreamFile("Ballaststoffe_Drink.txt", c)));
            this.setEiweissScore(new ScoreTabelle(FileManager.getInputStreamFile("Eiweiss_Drink.txt", c)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}