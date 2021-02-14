package com.example.nutriscore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.textView1 = findViewById(R.id.textView1);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this::buttonOnClick);
        EditText sugarInput = findViewById(R.id.InputSugar);
    }

    public void buttonOnClick(View v) {
        this.textView1.setText("Ausgabe");
    }


}