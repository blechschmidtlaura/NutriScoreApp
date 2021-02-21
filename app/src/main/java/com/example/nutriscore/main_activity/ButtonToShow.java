package com.example.nutriscore.main_activity;

import android.os.Build;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;

import androidx.annotation.RequiresApi;

import java.util.List;

public class ButtonToShow {
    private MainActivity mainActivity;
    private List<View> viewsToMakeVisible;
    public ButtonToShow(MainActivity mainActivity, List<View> viewsToMakeVisible) {
        this.mainActivity = mainActivity;
        this.viewsToMakeVisible = viewsToMakeVisible;
        this.mainActivity.buttonToShow.setOnClickListener(this::onClick);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick(View v) {
        this.fadeOutView(this.mainActivity.buttonToShow, this.mainActivity.BUTTON_TO_SHOW_FADE_OUT_DURATION);
        this.viewsToMakeVisible.forEach(view -> view.setVisibility(View.VISIBLE));
        this.mainActivity.buttonToShow.setVisibility(View.INVISIBLE);
    }

    public void fadeOutView(View v, int duration){
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(duration);
        v.startAnimation(anim);
    }
}
