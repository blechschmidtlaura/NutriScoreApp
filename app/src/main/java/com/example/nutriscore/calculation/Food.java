package com.example.nutriscore.calculation;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.example.nutriscore.FileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Food Klasse speichert für ein Nahrungsmittel alle wichtigen Inhaltstoffe
 * Es ist Parcelable damit es von einer Activity an die andere weitergegeben werden kann
 */
public class Food  extends Product {

    public Food(Context c, int energie, double zucker, double gesFettsaeuren, double natrium, int fruechteGemuese, double ballaststoffe, double eiweiss) {
        super(energie, zucker, gesFettsaeuren, natrium, fruechteGemuese, ballaststoffe, eiweiss);
        loadFiles(c);
    }

    /**
     * Die ScoreTabellen für berechnung des NutriScores werden aus dem Lokalen Speicher ausgelesen.
     * @param c Context wird benötigt um auf den Lokalen Speicher des Handys zuzugreifen in welchem die Tabellen gespeichert sind
     */

    private void loadFiles(Context c){
        try {
            this.setEnergieScore(new ScoreTabelle(FileManager.getInputStreamFile("Energiewert.txt", c)));
            this.setZuckerScore(new ScoreTabelle(FileManager.getInputStreamFile("Zuckerwert.txt", c)));
            this.setGesFettsaeurenScore(new ScoreTabelle(FileManager.getInputStreamFile("GesFettsaeuren.txt", c)));
            this.setNatriumScore(new ScoreTabelle(FileManager.getInputStreamFile("Natrium.txt", c)));
            this.setFruechteGemueseScore(new ScoreTabelle(FileManager.getInputStreamFile("FruechteGemuese.txt", c)));
            this.setBallaststoffeScore(new ScoreTabelle(FileManager.getInputStreamFile("Ballaststoffe.txt", c)));
            this.setEiweissScore(new ScoreTabelle(FileManager.getInputStreamFile("Eiweiss.txt", c)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
