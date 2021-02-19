package com.example.nutriscore.calculation;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.nutriscore.FileManager;

import java.io.IOException;

@RequiresApi(api = Build.VERSION_CODES.O)
public class NutriScore {
    private  ScoreTabelle energieScore;
    private  ScoreTabelle zuckerScore;
    private  ScoreTabelle gesFettsaeurenScore;
    private  ScoreTabelle natriumScore;
    private  ScoreTabelle fruechteGemueseScore;
    private  ScoreTabelle ballaststoffeScore;
    private  ScoreTabelle eiweissScore;

    public NutriScore(Context c) {
        try {
            energieScore = new ScoreTabelle(FileManager.getInputStreamFile("Energiewert.txt", c));
            zuckerScore = new ScoreTabelle(FileManager.getInputStreamFile("Zuckerwert.txt", c));
            gesFettsaeurenScore = new ScoreTabelle(FileManager.getInputStreamFile("GesFettsaeuren.txt", c));
            natriumScore = new ScoreTabelle(FileManager.getInputStreamFile("Natrium.txt", c));
            fruechteGemueseScore = new ScoreTabelle(FileManager.getInputStreamFile("FruechteGemuese.txt", c));
            ballaststoffeScore = new ScoreTabelle(FileManager.getInputStreamFile("Ballaststoffe.txt", c));
            eiweissScore = new ScoreTabelle(FileManager.getInputStreamFile("Eiweiss.txt", c));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public double calculateScore(Food food) {
        int energie = food.getEnergie();
        double zucker = food.getZucker();
        double gesFettsaeuren = food.getGesFettsaeuren();
        double natrium = food.getNatrium();
        int fruechteGemuese = food.getFruechteGemuese();
        double ballaststoffe = food.getBallaststoffe();
        double eiweiss = food.getEiweiss();

        double score = 0;
        double punkte = energie + zucker + gesFettsaeuren + natrium;
        if (punkte >= 11 && (fruechteGemuese >= -4 && fruechteGemuese <= -1)) {
            score = energieScore.get(energie) +
                    zuckerScore.get(zucker) +
                    gesFettsaeurenScore.get(gesFettsaeuren) +
                    natriumScore.get(natrium) +
                    fruechteGemueseScore.get(fruechteGemuese) +
                    ballaststoffeScore.get(ballaststoffe);
        }else{
            score = energieScore.get(energie) +
                    zuckerScore.get(zucker) +
                    gesFettsaeurenScore.get(gesFettsaeuren) +
                    natriumScore.get(natrium) +
                    fruechteGemueseScore.get(fruechteGemuese) +
                    ballaststoffeScore.get(ballaststoffe) +
                    eiweissScore.get(eiweiss);
        }
        return score;
    }

    private char nutriValue(int value){
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

    public char getScore(Food food) {
        int score = (int) calculateScore(food);
        return nutriValue(score);
    }
}
