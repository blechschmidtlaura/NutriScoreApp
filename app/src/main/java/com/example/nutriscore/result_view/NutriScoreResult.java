package com.example.nutriscore.result_view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nutriscore.R;
import com.example.nutriscore.barcode_scanner.BarCodeScanner;
import com.example.nutriscore.calculation.Food;
import com.example.nutriscore.calculation.NutriScore;

/**
 * Die NutriScoreResult
 * soll die Ergebnisse anzeigen
 * sowohl die Nahrungswerte als auch den Nutri Score
 * und nochmaliges Scannen ermöglichen
 */
public class NutriScoreResult extends AppCompatActivity {
    private TextView scoreView;
    private Button scanButton;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutri_score_result);
        this.scoreView = findViewById(R.id.nutriScoreResult);
        this.scanButton = findViewById(R.id.scanAgain);

        this.scanButton.setOnClickListener(this::changeToBarcodeActivity);
        Intent intent = getIntent();
        int textColor;
        if (intent.hasExtra("food")){
            Food f = intent.getExtras().getParcelable("food");
            NutriScore nutriScore = new NutriScore(this.getApplicationContext());
            char score = nutriScore.getScore(f);

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

    }
    protected void changeToBarcodeActivity(View v){
        Intent intent = new Intent(this, BarCodeScanner.class);
        startActivity(intent);
    }
}