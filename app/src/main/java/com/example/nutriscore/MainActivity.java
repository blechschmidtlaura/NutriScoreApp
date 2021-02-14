package com.example.nutriscore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final int BUTTON_TO_SHOW_FADE_OUT_DURATION = 1000;
    private Button buttonToShow;
    private TextView nutriScoreTextView;
    private EditText sugarInput;
    private Button calculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.nutriScoreTextView = findViewById(R.id.textView1);
        this.sugarInput = findViewById(R.id.InputSugar);

        this.buttonToShow = findViewById(R.id.button);
        buttonToShow.setOnClickListener(this::buttonToShowOnClick);
        this.calculate = findViewById(R.id.button2);
        calculate.setOnClickListener(this::calculateOnClick);
    }

    private void fadeOutView(View v, int duration){
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(duration);
        v.startAnimation(anim);
    }

    private void buttonToShowOnClick(View v) {
        this.fadeOutView(this.buttonToShow, this.BUTTON_TO_SHOW_FADE_OUT_DURATION);
        this.sugarInput.setVisibility(View.VISIBLE);
        this.calculate.setVisibility(View.VISIBLE);
        this.buttonToShow.setVisibility(View.INVISIBLE);
    }

    private void calculateOnClick(View v) {
        int amountSugar = Integer.parseInt(sugarInput.getText().toString());
        String score = String.valueOf(NutriScore.getScore(amountSugar));
        this.nutriScoreTextView.setText(getString(R.string.dein_nutri_score) + score);
        this.hideKeyboard(this.nutriScoreTextView);
    }

    private void hideKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }
}