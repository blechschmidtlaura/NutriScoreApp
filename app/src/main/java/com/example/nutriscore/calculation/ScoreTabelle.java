package com.example.nutriscore.calculation;


import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * ScoreTabelle
 * Liest die Tabelle zur Berechnung des NutriScores aus einer Text Datei aus
 */
public class ScoreTabelle implements Parcelable {

    private List<Double> scores;
    private List<Double> bounds;

    /**
     * Initialisierung der ScoreTabelle
     * @param inputStream Stream der Text Datei
     */
    public ScoreTabelle(InputStream inputStream){
        scores = new LinkedList<>();
        bounds = new LinkedList<>();
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            input.readLine(); //Kopfzeile ignorieren
            String zeile = null;
            while ((zeile = input.readLine()) != null){
                String[] parts = zeile.split(";");
                double score = Double.parseDouble(parts[0]);
                double bound = Double.parseDouble(parts[1]);
                scores.add(score);
                bounds.add(bound);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ScoreTabelle(String dateiname) {
        scores = new LinkedList<Double>();
        bounds = new LinkedList<Double>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(dateiname));
            input.readLine(); //Kopfzeile ignorieren
            String zeile = null;
            while ((zeile = input.readLine()) != null){
                String[] parts = zeile.split(";");
                double score = Double.parseDouble(parts[0]);
                double bound = Double.parseDouble(parts[1]);
                scores.add(score);
                bounds.add(bound);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ScoreTabelle(Parcel in) {
        List<List<Double>> myList = new ArrayList<>();
        in.readList(myList,List.class.getClassLoader());
        scores = myList.get(0);
        bounds = myList.get(1);
    }

    /**
     * Berechnet den Score für einen Wert input
     * @param input der Wert
     * @return der entsprechende Score für den input Wert
     */
    public double get(double input) {
        for (int i = 0; i < scores.size(); i++) {
            if (input <= bounds.get(i)) {
                return scores.get(i);
            }
        }
        return scores.get(scores.size()-1);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(List.of(scores, bounds));
    }

    public static final Parcelable.Creator<ScoreTabelle> CREATOR
            = new Parcelable.Creator<ScoreTabelle>() {
        public ScoreTabelle createFromParcel(Parcel in) {
            return new ScoreTabelle(in);
        }

        public ScoreTabelle[] newArray(int size) {
            return new ScoreTabelle[size];
        }
    };
}
