package com.example.nutriscore;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
public class FileManager {
    public static void main(String[] args) {
        System.out.println();
    }


    public static InputStream getInputStreamFile(String fileName, Context c) throws IOException {
        return c.getResources().getAssets().open(fileName);
    }

}
