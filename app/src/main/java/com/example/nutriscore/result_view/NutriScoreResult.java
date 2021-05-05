package com.example.nutriscore.result_view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutriscore.R;
import com.example.nutriscore.barcode_scanner.BarCodeScanner;
import com.example.nutriscore.calculation.ElasticsearchHandler;
import com.example.nutriscore.calculation.Food;
import com.example.nutriscore.calculation.NutriScore;
import com.example.nutriscore.calculation.Product;
import org.json.JSONException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Die NutriScoreResult
 * soll die Ergebnisse anzeigen
 * sowohl die Nahrungswerte als auch den Nutri Score
 * und nochmaliges Scannen ermöglichen
 */
public class NutriScoreResult extends AppCompatActivity {
    private TextView scoreView;
    private Button scanButton;
    private EditText barcode;
    private Button resultBarcode;
    private FoodDisplay foodDisplay;
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutri_score_result);
        this.scoreView = findViewById(R.id.nutriScoreResult);
        this.scanButton = findViewById(R.id.scanAgain);
        this.barcode = findViewById(R.id.InputBarcode);
        this.scanButton.setOnClickListener(this::changeToBarcodeActivity);
        foodDisplay = new FoodDisplay(this);

        Intent intent = getIntent();
        if (intent.hasExtra("food")){
            Product product = intent.getExtras().getParcelable("food");
            foodDisplay.autoFillFood(product);
            char score = NutriScore.getScore(product);
            showResult(score);
        }
        this.resultBarcode = findViewById(R.id.buttonErgebnis);
        this.resultBarcode.setOnClickListener(this::getResult);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void getResult(View v){
        char result;
        if(barcode.getText() != null){
            String ean = barcode.getText().toString();
            final List<Optional<Product>> products = new LinkedList<>();
            Context c = this.getApplicationContext();
            // Die ElasticSearch Request wird auf einem anderen Thread ausgeführt
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() throws IllegalArgumentException{
                    try {
                        products.add(ElasticsearchHandler.getFoodByEAN(ean, c));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.start();
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Optional<Product> optionalFood = products.get(0);
            Product product1;
            if(optionalFood.isPresent()){
                product1 = optionalFood.get();
                foodDisplay.autoFillFood(product1);
                result = NutriScore.getScore(product1);
                showResult(result);
            } else{
                Toast.makeText(this, "Kein Nahrungsmittel gefunden!", Toast.LENGTH_LONG).show();
            }
        }
    }

    protected void showResult(char score){
        int textColor;
        switch (score){
            case 'A':
                textColor = Color.GREEN;
                break;
            case 'B':
                textColor = Color.YELLOW;
                break;
            case 'C':
                textColor = Color.rgb(100, 100, 0);
                break;
            case 'D':
                textColor = Color.RED;
                break;
            case 'E':
                textColor = Color.rgb(60, 0, 0);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + score);
        }
        this.scoreView.setTextColor(textColor);
        this.scoreView.setText(String.valueOf(score));
    }


    protected void changeToBarcodeActivity(View v){
        Intent intent = new Intent(this, BarCodeScanner.class);
        startActivity(intent);
    }
}