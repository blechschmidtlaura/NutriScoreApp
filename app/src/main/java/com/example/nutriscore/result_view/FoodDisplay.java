package com.example.nutriscore.result_view;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.nutriscore.R;
import com.example.nutriscore.calculation.Food;
import com.example.nutriscore.calculation.Product;
import com.example.nutriscore.main_activity.MainActivity;

import java.util.LinkedList;
import java.util.List;

public class FoodDisplay{
    public EditText energyInput;
    public EditText sugarInput;
    public EditText greaseInput;
    public EditText natriumInput;
    public EditText fruitVegetableInput;
    public EditText fibreInput;
    public EditText proteinInput;
    public TextView energyText;
    public TextView sugarText;
    public TextView greaseText;
    public TextView natriumText;
    public TextView fruitText;
    public TextView fibreText;
    public TextView proteinText;
    public List<View> viewsToMakeVisible = new LinkedList<>();
    public List<EditText> textInputs = new LinkedList<>();

    @RequiresApi(api = Build.VERSION_CODES.R)
    public FoodDisplay(Activity activity){
        findViews(activity);
        this.viewsToMakeVisible = List.of(this.sugarInput, this.energyInput, this.fibreInput, this.fruitVegetableInput, this.greaseInput, this.natriumInput, this.proteinInput, this.energyText, this.sugarText, this.greaseText, this.fibreText, this.fruitText, this.natriumText, this.proteinText);
        this.textInputs = List.of(this.energyInput, this.sugarInput, this.greaseInput, this.natriumInput, this.fruitVegetableInput, this.fibreInput, this.proteinInput);

    }

    public void autoFillFood(Product product){
        ((EditText) viewsToMakeVisible.get(0)).setText(String.valueOf(product.getZucker()));
        ((EditText) viewsToMakeVisible.get(1)).setText(String.valueOf(product.getEnergie()));
        ((EditText) viewsToMakeVisible.get(2)).setText(String.valueOf(product.getBallaststoffe()));
        ((EditText) viewsToMakeVisible.get(3)).setText(String.valueOf(product.getFruechteGemuese()));
        ((EditText) viewsToMakeVisible.get(4)).setText(String.valueOf(product.getGesFettsaeuren()));
        ((EditText) viewsToMakeVisible.get(5)).setText(String.valueOf(product.getNatrium()));
        ((EditText) viewsToMakeVisible.get(6)).setText(String.valueOf(product.getEiweiss()));
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    protected void findViews(Activity activity){
        this.sugarInput = activity.findViewById(R.id.InputSugar);
        this.energyInput = activity.findViewById(R.id.InputEnergy);
        this.greaseInput = activity.findViewById(R.id.InputGrease);
        this.natriumInput = activity.findViewById(R.id.InputNatrium);
        this.fruitVegetableInput = activity.findViewById(R.id.InputFruitVegetable);
        this.fibreInput = activity.findViewById(R.id.InputFibre);
        this.proteinInput = activity.findViewById(R.id.InputProtein);

        this.energyText = activity.findViewById(R.id.TextEnergy);
        this.sugarText = activity.findViewById(R.id.TextSugar);
        this.greaseText = activity.findViewById(R.id.TextGrease);
        this.natriumText = activity.findViewById(R.id.TextNatrium);
        this.fruitText = activity.findViewById(R.id.TextFruitVegetable);
        this.fibreText = activity.findViewById(R.id.TextFibre);
        this.proteinText = activity.findViewById(R.id.TextProtein);
    }
}
