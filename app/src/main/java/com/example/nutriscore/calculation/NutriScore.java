package com.example.nutriscore.calculation;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.nutriscore.FileManager;
@RequiresApi(api = Build.VERSION_CODES.O)
public class NutriScore {
    private static final ScoreTabelle energieScore = new ScoreTabelle(FileManager.getPathToFileString("Energiewert.txt"));
    private static final ScoreTabelle zuckerScore = new ScoreTabelle(FileManager.getPathToFileString("Zuckerwert.txt"));
    private static final ScoreTabelle gesFettsaeurenScore = new ScoreTabelle(FileManager.getPathToFileString("GesFettsaeuren.txt"));
    private static final ScoreTabelle natriumScore = new ScoreTabelle(FileManager.getPathToFileString("Natrium.txt"));
    private static final ScoreTabelle fruechteGemueseScore = new ScoreTabelle(FileManager.getPathToFileString("FruechteGemuese.txt"));
    private static final ScoreTabelle ballaststoffeScore = new ScoreTabelle(FileManager.getPathToFileString("Ballaststoffe.txt"));
    private static final ScoreTabelle eiweissScore = new ScoreTabelle(FileManager.getPathToFileString("Eiweiss.txt"));

    public static double calculateScore(Food food) {
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

    public static char getScore(Food food) {
        int score = (int) calculateScore(food);
        return nutriValue(score);
    }
}
