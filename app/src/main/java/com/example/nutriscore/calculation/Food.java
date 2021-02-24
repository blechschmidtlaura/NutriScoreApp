package com.example.nutriscore.calculation;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class Food  implements Parcelable {
    private int energie;
    private double zucker;
    private double gesFettsaeuren;
    private double natrium;
    private int fruechteGemuese;
    private double ballaststoffe;
    private double eiweiss;

    public Food(int energie, double zucker, double gesFettsaeuren, double natrium, int fruechteGemuese, double ballaststoffe, double eiweiss) {
        this.intitializeValues(energie, zucker, gesFettsaeuren, natrium, fruechteGemuese, ballaststoffe, eiweiss);
    }
    public void intitializeValues(int energie, double zucker, double gesFettsaeuren, double natrium, int fruechteGemuese, double ballaststoffe, double eiweiss){
        this.energie = energie;
        this.zucker = zucker;
        this.gesFettsaeuren = gesFettsaeuren;
        this.natrium = natrium;
        this.fruechteGemuese = fruechteGemuese;
        this.ballaststoffe = ballaststoffe;
        this.eiweiss = eiweiss;
    }
    public void initializeValues(List<Double> doubles){
        intitializeValues(doubles.get(0).intValue(), doubles.get(1), doubles.get(2), doubles.get(3), doubles.get(4).intValue(), doubles.get(5), doubles.get(6));

    }

    public Food(List<Double> doubles){
        initializeValues(doubles);
    }

    public Food(double zucker) {
        this(0, zucker, 0, 0, 0, 0, 0);
    }

    public int getEnergie() {
        return energie;
    }

    public double getZucker() {
        return zucker;
    }

    public double getGesFettsaeuren() {
        return gesFettsaeuren;
    }

    public double getNatrium() {
        return natrium;
    }

    public int getFruechteGemuese() {
        return fruechteGemuese;
    }

    public double getBallaststoffe() {
        return ballaststoffe;
    }

    public double getEiweiss() {
        return eiweiss;
    }


    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void writeToParcel(Parcel out, int flags) {
        out.writeList(List.of((double)energie, (double)zucker, (double)gesFettsaeuren, (double)natrium, (double)fruechteGemuese, (double)ballaststoffe, (double)eiweiss));
    }

    public static final Parcelable.Creator<Food> CREATOR
            = new Parcelable.Creator<Food>() {
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    private Food(Parcel in) {
        List<Double> myList = new ArrayList<>();
        in.readList(myList,List.class.getClassLoader());
        initializeValues(myList);
    }
}
