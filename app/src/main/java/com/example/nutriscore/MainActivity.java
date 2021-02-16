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
    public final int BUTTON_TO_SHOW_FADE_OUT_DURATION = 1000;
    public Button buttonToShow;
    public TextView nutriScoreTextView;
    public EditText sugarInput;
    public Button calculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.findViews();
        ButtonToShow btn= new ButtonToShow(this);
        CalculateButton calculateButton = new CalculateButton(this);
    }

    protected void findViews(){
        this.nutriScoreTextView = findViewById(R.id.textView1);
        this.sugarInput = findViewById(R.id.InputSugar);
        this.buttonToShow = findViewById(R.id.button);
        this.calculate = findViewById(R.id.button2);
    }

}