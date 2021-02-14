package com.example.nutriscore;

import android.view.View;
import android.view.animation.AlphaAnimation;

public class ButtonToShow {
    private MainActivity mainActivity;

    public ButtonToShow(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void onClick(View v) {
        this.fadeOutView(this.mainActivity.buttonToShow, this.mainActivity.BUTTON_TO_SHOW_FADE_OUT_DURATION);
        this.mainActivity.sugarInput.setVisibility(View.VISIBLE);
        this.mainActivity.calculate.setVisibility(View.VISIBLE);
        this.mainActivity.buttonToShow.setVisibility(View.INVISIBLE);
    }

    public void fadeOutView(View v, int duration){
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(duration);
        v.startAnimation(anim);
    }
}
