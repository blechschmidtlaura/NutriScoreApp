package com.example.nutriscore.information;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nutriscore.FileManager;
import com.example.nutriscore.R;
import com.example.nutriscore.calculation.ScoreTabelle;

import org.w3c.dom.Text;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InformationNutriScore extends AppCompatActivity {

    private TextView informationText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_nutri_score);
        getInformationText();
        //TextView anzeigen, erstellen und id finden und zuschreiben
    }

    protected void getInformationText(){
        String text = "";
        try {
            InputStream information = getResources().getAssets().open("Information");
            int size = information.available();
            byte buffer[] = new byte[size];
            information.read(buffer);
            information.close();
            text = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        informationText.setText(text);
    }

}
