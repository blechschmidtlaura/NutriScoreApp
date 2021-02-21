package com.example.nutriscore.main_activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nutriscore.R;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final int BUTTON_TO_SHOW_FADE_OUT_DURATION = 1000;
    public Button buttonToShow;
    public TextView nutriScoreTextView;
    public EditText energyInput;
    public EditText sugarInput;
    public EditText greaseInput;
    public EditText natriumInput;
    public EditText fruitVegetableInput;
    public EditText fibreInput;
    public EditText proteinInput;
    public Button calculate;

    public List<View> viewsToMakeVisible = new LinkedList<>();
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.findViews();
        this.viewsToMakeVisible = List.of(this.sugarInput, this.energyInput, this.fibreInput, this.fruitVegetableInput, this.greaseInput, this.natriumInput, this.proteinInput, this.calculate);
        ButtonToShow btn= new ButtonToShow(this, viewsToMakeVisible);
        CalculateButton calculateButton = new CalculateButton(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    protected void findViews(){
        this.nutriScoreTextView = findViewById(R.id.textView1);
        this.sugarInput = findViewById(R.id.InputSugar);
        this.energyInput = findViewById(R.id.InputEnergy);
        this.greaseInput = findViewById(R.id.InputGrease);
        this.natriumInput = findViewById(R.id.InputNatrium);
        this.fruitVegetableInput = findViewById(R.id.InputFruitVegetable);
        this.fibreInput = findViewById(R.id.InputFibre);
        this.proteinInput = findViewById(R.id.InputProtein);
        this.buttonToShow = findViewById(R.id.button);
        this.calculate = findViewById(R.id.button2);
    }

}