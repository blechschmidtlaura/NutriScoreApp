package com.example.nutriscore.main_activity;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.nutriscore.R;
import com.example.nutriscore.calculation.ElasticsearchHandler;
import com.example.nutriscore.calculation.Food;
import com.example.nutriscore.calculation.NutriScore;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CalculateButton {
    private MainActivity mainActivity;
    private List<EditText> inputTexts;
    public CalculateButton(MainActivity mainActivity,  List<EditText> inputTexts) {
        this.inputTexts = inputTexts;
        this.mainActivity = mainActivity;
        this.mainActivity.calculate.setOnClickListener(this::onClick);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClick(View v) {
        try {
            Food food = this.getFoodFromInput();
            NutriScore nutriScore = new NutriScore(mainActivity.getApplicationContext());
            char score = nutriScore.getScore(food);
            this.mainActivity.nutriScoreTextView.setText(this.mainActivity.getString(R.string.dein_nutri_score) + score);
            this.hideKeyboard(this.mainActivity.nutriScoreTextView);
        }catch(NumberFormatException e){
            Toast.makeText(this.mainActivity.getApplicationContext(), "Die Eingegebenen müssen Strings sein!", Toast.LENGTH_SHORT).show();
        }

        Executors.newSingleThreadExecutor().submit(() -> {
            final String ean = "20150907";
            try {
                final Food food = ElasticsearchHandler.getFoodByEAN(ean);
                final String score = String.valueOf(new NutriScore(mainActivity.getApplicationContext()).calculateScore(food));
                mainActivity.runOnUiThread(() -> Toast.makeText(mainActivity, score, Toast.LENGTH_LONG).show());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Food getFoodFromInput() throws NumberFormatException{
        return new Food(this.inputTexts.stream().map(this::getInput).collect(Collectors.toList()));
    }

    public double getInput(EditText editText) throws NumberFormatException{
        String s = editText.getText().toString();
        if(s.equals("")){
            return 0d;
        }else {
            return Double.parseDouble(s);
        }
    }


    public void hideKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)this.mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }
}
