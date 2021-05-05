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
public class ScoreTabelle{

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

}
