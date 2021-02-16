package com.example.nutriscore;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class CalculateButton {
    private MainActivity mainActivity;

    public CalculateButton(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.mainActivity.calculate.setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        // TODO: manage not integer Inputs
        int amountSugar = Integer.parseInt(this.mainActivity.sugarInput.getText().toString());
        String score = String.valueOf(NutriScore.getScore(amountSugar));
        this.mainActivity.nutriScoreTextView.setText(this.mainActivity.getString(R.string.dein_nutri_score) + score);
        this.hideKeyboard(this.mainActivity.nutriScoreTextView);
    }


    public void hideKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)this.mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }
}
