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

    public static double calculateScore(int energie, double zucker, double gesFettsaeuren, double natrium, int fruechteGemuese, double ballaststoffe, double eiweiss) {

        double score = 0;
        double punkte = energie + zucker + gesFettsaeuren + natrium;
        if (punkte >= 11 && (fruechteGemuese >= -4 && fruechteGemuese <= -1)) {
            score = energieScore.get(energie) +
                    zuckerScore.get((float) zucker) +
                    gesFettsaeurenScore.get((float) gesFettsaeuren) +
                    natriumScore.get((float) natrium) +
                    fruechteGemueseScore.get(fruechteGemuese) +
                    ballaststoffeScore.get((float) ballaststoffe);
        }else{
            score = energieScore.get(energie) +
                    zuckerScore.get((float) zucker) +
                    gesFettsaeurenScore.get((float) gesFettsaeuren) +
                    natriumScore.get((float) natrium) +
                    fruechteGemueseScore.get(fruechteGemuese) +
                    ballaststoffeScore.get((float) ballaststoffe) +
                    eiweissScore.get((float) eiweiss);
        }
        return score;
    }

    private static String nutriValue(int value){
        if(value <=-1) {
            return "A";
        }else if(value <=2){
            return "B";
        }else if(value <= 10){
            return "C";
        }else if(value <= 18){
            return "D";
        }else{
            return "E";
        }
    }

    public static int getScore(int amountSugar) {
        return 0;
    }
}
