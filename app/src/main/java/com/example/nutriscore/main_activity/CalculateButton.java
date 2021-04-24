package com.example.nutriscore.main_activity;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.nutriscore.R;
import com.example.nutriscore.calculation.Food;
import com.example.nutriscore.calculation.NutriScore;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Der Calculate Button berechnet beim Klicken den NutriScore der eingegebenen Nahrungswerte
 */
public class CalculateButton {
    private MainActivity mainActivity;
    private List<EditText> inputTexts;
    public CalculateButton(MainActivity mainActivity,  List<EditText> inputTexts) {
        this.inputTexts = inputTexts;
        this.mainActivity = mainActivity;
        this.mainActivity.calculate.setOnClickListener(this::onClick);
    }

    /**
     * Onclick Listener wird beim Klicken aufgerufen
     * Der NutriScore wird berechnet und angezeigt
     * @param v
     */
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


    /**
     * Die Tastatur wird ausgeblendet
     * @param v  Die View
     */
    public void hideKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)this.mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }
}
