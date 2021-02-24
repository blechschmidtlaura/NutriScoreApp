package com.example.nutriscore.main_activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nutriscore.R;
import com.example.nutriscore.barcode_scanner.BarCodeScanner;

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
    public Button buttonScanner;
    public TextView energyText;
    public TextView sugarText;
    public TextView greaseText;
    public TextView natriumText;
    public TextView fruitText;
    public TextView fibreText;
    public TextView proteinText;

    public List<View> viewsToMakeVisible = new LinkedList<>();
    public List<EditText> textInputs = new LinkedList<>();
    public List<TextView> textsToMakeVisible = new LinkedList<>();
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.findViews();
        this.viewsToMakeVisible = List.of(this.sugarInput, this.energyInput, this.fibreInput, this.fruitVegetableInput, this.greaseInput, this.natriumInput, this.proteinInput, this.calculate, this.energyText, this.sugarText, this.greaseText, this.fibreText, this.fruitText, this.natriumText, this.proteinText);
        this.textInputs = List.of(this.energyInput, this.sugarInput, this.greaseInput, this.natriumInput, this.fruitVegetableInput, this.fibreInput, this.proteinInput);
        ButtonToShow btn= new ButtonToShow(this, viewsToMakeVisible);
        CalculateButton calculateButton = new CalculateButton(this, textInputs);

        this.buttonScanner.setOnClickListener(this::changeActivity);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    protected void findViews(){
        this.nutriScoreTextView = findViewById(R.id.nutriScoreShower);
        this.sugarInput = findViewById(R.id.InputSugar);
        this.energyInput = findViewById(R.id.InputEnergy);
        this.greaseInput = findViewById(R.id.InputGrease);
        this.natriumInput = findViewById(R.id.InputNatrium);
        this.fruitVegetableInput = findViewById(R.id.InputFruitVegetable);
        this.fibreInput = findViewById(R.id.InputFibre);
        this.proteinInput = findViewById(R.id.InputProtein);
        this.buttonToShow = findViewById(R.id.showButton);
        this.calculate = findViewById(R.id.calculateNutriScoreButton);
        this.buttonScanner = findViewById(R.id.scannerButton);

        this.energyText = findViewById(R.id.TextEnergy);
        this.sugarText = findViewById(R.id.TextSugar);
        this.greaseText = findViewById(R.id.TextGrease);
        this.natriumText = findViewById(R.id.TextNatrium);
        this.fruitText = findViewById(R.id.TextFruitVegetable);
        this.fibreText = findViewById(R.id.TextFibre);
        this.proteinText = findViewById(R.id.TextProtein);
    }

    protected void changeActivity(View v){
        Intent intent = new Intent(this, BarCodeScanner.class);
        startActivity(intent);
    }

}