package com.example.nutriscore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView1;
    private EditText sugarInput;
    private Button calculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.textView1 = findViewById(R.id.textView1);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this::buttonOnClick);
        this.sugarInput = findViewById(R.id.InputSugar);
        this.calculate = findViewById(R.id.button2);
        calculate.setOnClickListener(this::calculateOnClick);
    }

    public void buttonOnClick(View v) {
        this.sugarInput.setVisibility(View.VISIBLE);
        this.calculate.setVisibility(View.VISIBLE);
    }

    public void calculateOnClick(View v) {
        Editable input = sugarInput.getText();
        this.textView1.setText(input);
    }
}