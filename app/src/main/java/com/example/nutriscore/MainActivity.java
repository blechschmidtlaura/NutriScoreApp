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
    private Button buttonToShow;
    private TextView textView1;
    private EditText sugarInput;
    private Button calculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.textView1 = findViewById(R.id.textView1);
        this.buttonToShow = findViewById(R.id.button);
        buttonToShow.setOnClickListener(this::buttonOnClick);
        this.sugarInput = findViewById(R.id.InputSugar);
        this.calculate = findViewById(R.id.button2);
        calculate.setOnClickListener(this::calculateOnClick);
    }

    public void buttonOnClick(View v) {
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(1000);
        buttonToShow.startAnimation(anim);
        this.sugarInput.setVisibility(View.VISIBLE);
        this.calculate.setVisibility(View.VISIBLE);
        this.buttonToShow.setVisibility(View.INVISIBLE);
    }

    public void calculateOnClick(View v) {
        String sugarInputText = sugarInput.getText().toString();
        //TODO: Rechnung für den Score hinzufügen
        String oldText = this.textView1.getText().toString();
        this.textView1.setText(oldText + " " + sugarInputText);
        this.hideKeyboard(this.textView1);
    }

    private void hideKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }
}