public class NutriScore {

    public static void main(String[] args) {

        System.out.println(nutriScore(123,50,3,6,4,78,55));

        System.out.println(nutriScore(350,0.5,0.1,0.01,0,2.1,7.4));

        System.out.println(nutriScore(149,5.7,0.5,0.76,6,5.3,7.9));
    }
    public static String nutriScore(int energie, double zucker, double gesFettsaeuren, double natrium, int fruechteGemuese, double ballaststoffe, double eiweiss) {
        ScoreTabelle energieScore = new ScoreTabelle("Energiewert.txt");
        ScoreTabelle zuckerScore = new ScoreTabelle("Zuckerwert.txt");
        ScoreTabelle gesFettsaeurenScore = new ScoreTabelle("GesFettsaeuren.txt");
        ScoreTabelle natriumScore = new ScoreTabelle("Natrium.txt");
        ScoreTabelle fruechteGemueseScore = new ScoreTabelle("FruechteGemuese.txt");
        ScoreTabelle ballaststoffeScore = new ScoreTabelle("Ballaststoffe.txt");
        ScoreTabelle eiweissScore = new ScoreTabelle("Eiweiss.txt");

        double punkte = energie + zucker + gesFettsaeuren + natrium;
        if (punkte >= 11 && (fruechteGemuese >= -4 && fruechteGemuese <= -1)) {
            return nutriValue(energieScore.get(energie) + zuckerScore.get((float) zucker) + gesFettsaeurenScore.get((float) gesFettsaeuren) + natriumScore.get((float) natrium) + fruechteGemueseScore.get(fruechteGemuese) + ballaststoffeScore.get((float) ballaststoffe));
        }
        return nutriValue(energieScore.get(energie) + zuckerScore.get((float) zucker) + gesFettsaeurenScore.get((float) gesFettsaeuren) + natriumScore.get((float) natrium) + fruechteGemueseScore.get(fruechteGemuese) + ballaststoffeScore.get((float) ballaststoffe) + eiweissScore.get((float) eiweiss));
    }

    public static String nutriValue(int value){
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
}
