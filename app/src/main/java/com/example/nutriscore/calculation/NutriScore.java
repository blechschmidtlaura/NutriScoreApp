package com.example.nutriscore.calculation;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.nutriscore.FileManager;

import java.io.IOException;

/**
 * NutriScore Klasse
 * berechnet den NutriScore für ein Nahrungsmittel
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class NutriScore {

    /**
     * Berechnet den NutriScore
     * private?
     * @param product Das Nahrungsmittel
     * @return Gibt einen Score als DOuble zurück
     */
    public static double calculateScore(Product product) {
        int energie = product.getEnergie();
        double zucker = product.getZucker();
        double gesFettsaeuren = product.getGesFettsaeuren();
        double natrium = product.getNatrium();
        int fruechteGemuese = product.getFruechteGemuese();
        double ballaststoffe = product.getBallaststoffe();
        double eiweiss = product.getEiweiss();

        double score = 0;
        double punkte = energie + zucker + gesFettsaeuren + natrium;
        score = product.getEnergieScore().get(energie) +
                product.getZuckerScore().get(zucker) +
                product.getGesFettsaeurenScore().get(gesFettsaeuren) +
                product.getNatriumScore().get(natrium) +
                product.getFruechteGemueseScore().get(fruechteGemuese) +
                product.getBallaststoffeScore().get(ballaststoffe);
        if (punkte >= 11 && (fruechteGemuese >= -4 && fruechteGemuese <= -1)) {
            score = score;
        }else{
            score = score + product.getEiweissScore().get(eiweiss);
        }
        return score;
    }

    /**
     * Berechnet aus dem Zahlen Wert den Buchstaben
     * @param value Zahlen Wert
     * @return Buchstaben Nutri Wert
     */
    private static char nutriValue(int value){
        if(value <=-1) {
            return 'A';
        }else if(value <=2){
            return 'B';
        }else if(value <= 10){
            return 'C';
        }else if(value <= 18){
            return 'D';
        }else{
            return 'E';
        }
    }

    /**
     * Berechnet den NutriScore aus Fod Objekt
     * @param product  Food Objekt
     * @return gibt den Buchstaben Nutri Wert zurück
     */
    public static char getScore(Product product) {
        int score = (int) calculateScore(product);
        return nutriValue(score);
    }
}
