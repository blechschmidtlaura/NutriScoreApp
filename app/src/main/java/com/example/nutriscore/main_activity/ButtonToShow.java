package com.example.nutriscore.main_activity;

import android.view.View;
import android.view.animation.AlphaAnimation;

public class ButtonToShow {
    private MainActivity mainActivity;

    public ButtonToShow(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.mainActivity.buttonToShow.setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        this.fadeOutView(this.mainActivity.buttonToShow, this.mainActivity.BUTTON_TO_SHOW_FADE_OUT_DURATION);
        this.mainActivity.sugarInput.setVisibility(View.VISIBLE);
        this.mainActivity.energyInput.setVisibility(View.VISIBLE);
        this.mainActivity.greaseInput.setVisibility(View.VISIBLE);
        this.mainActivity.natriumInput.setVisibility(View.VISIBLE);
        this.mainActivity.fruitVegetableInput.setVisibility(View.VISIBLE);
        this.mainActivity.fibreInput.setVisibility(View.VISIBLE);
        this.mainActivity.proteinInput.setVisibility(View.VISIBLE);
        this.mainActivity.calculate.setVisibility(View.VISIBLE);
        this.mainActivity.buttonToShow.setVisibility(View.INVISIBLE);
    }

    public void fadeOutView(View v, int duration){
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(duration);
        v.startAnimation(anim);
    }
}
