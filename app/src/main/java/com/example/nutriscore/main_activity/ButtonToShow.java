package com.example.nutriscore.main_activity;

import android.os.Build;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.nutriscore.calculation.ElasticsearchHandler;
import com.example.nutriscore.calculation.Food;
import com.example.nutriscore.calculation.NutriScore;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * Wenn der ButtonToShow gecklickt wird dann werden alle übergebenen Views sichtbar gemacht.
 * Und darauf wird der Button selbst unsichtbar
 */
public class ButtonToShow {
    private MainActivity mainActivity;
    private List<View> viewsToMakeVisible;
    public ButtonToShow(MainActivity mainActivity, List<View> viewsToMakeVisible) {
        this.mainActivity = mainActivity;
        this.viewsToMakeVisible = viewsToMakeVisible;
        this.mainActivity.buttonToShow.setOnClickListener(this::onClick);
    }


    /**
     * onclick Funktion wenn der Button angecklickt wird
     * alle Views werden sichtbar gemacht
     * der Button selbst wird unsichtbar
     * Alle Nahrungsfelder werden automatisch gefüllt.
     * @param v Die View des Buttons ???
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick(View v) {
        this.fadeOutView(this.mainActivity.buttonToShow, this.mainActivity.BUTTON_TO_SHOW_FADE_OUT_DURATION);
        this.viewsToMakeVisible.forEach(view -> view.setVisibility(View.VISIBLE));
        this.mainActivity.buttonToShow.setVisibility(View.INVISIBLE);

        Executors.newSingleThreadExecutor().submit(() -> {
            // TODO: Get EAN from text field
            final String ean = "20150907";
            try {
                final Food food = ElasticsearchHandler.getFoodByEAN(ean).get();
                mainActivity.runOnUiThread(() -> {
                    mainActivity.autoFillFood(food);
                });
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                mainActivity.runOnUiThread(() -> Toast.makeText(mainActivity, "Couldn't fetch product data.", Toast.LENGTH_LONG).show());
            }
        });
    }

    /**
     * Eine View wird langsam ausgeblendet
     * @param v  die View
     * @param duration die Dauer der ANimation
     */
    public void fadeOutView(View v, int duration){
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(duration);
        v.startAnimation(anim);
    }
}
