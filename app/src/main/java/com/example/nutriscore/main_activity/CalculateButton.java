package com.example.nutriscore.main_activity;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.RequiresApi;

import com.example.nutriscore.R;
import com.example.nutriscore.calculation.Food;
import com.example.nutriscore.calculation.NutriScore;

public class CalculateButton {
    private MainActivity mainActivity;

    public CalculateButton(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.mainActivity.calculate.setOnClickListener(this::onClick);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClick(View v) {
        // TODO: manage not integer Inputs
        // TODO: improve  use List to get all values
        int amountSugar = Integer.parseInt(this.mainActivity.sugarInput.getText().toString());
        int amountGrease = Integer.parseInt(this.mainActivity.greaseInput.getText().toString());
        int amountFibre = Integer.parseInt(this.mainActivity.fibreInput.getText().toString());
        int amountFruitVegetable = Integer.parseInt(this.mainActivity.fruitVegetableInput.getText().toString());
        int amountNatrium = Integer.parseInt(this.mainActivity.natriumInput.getText().toString());
        int amountProtein = Integer.parseInt(this.mainActivity.proteinInput.getText().toString());
        int amountEnergy = Integer.parseInt(this.mainActivity.energyInput.getText().toString());
        Food food = new Food(amountEnergy, amountSugar, amountGrease, amountNatrium, amountFruitVegetable, amountFibre, amountProtein);
        NutriScore nutriScore = new NutriScore(mainActivity.getApplicationContext());
        char score = nutriScore.getScore(food);
        this.mainActivity.nutriScoreTextView.setText(this.mainActivity.getString(R.string.dein_nutri_score) + score);
        this.hideKeyboard(this.mainActivity.nutriScoreTextView);
    }


    public void hideKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)this.mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }
}
