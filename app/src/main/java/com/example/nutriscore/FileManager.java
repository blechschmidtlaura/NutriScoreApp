package com.example.nutriscore;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.nio.file.Path;
import java.nio.file.Paths;
public class FileManager {
    public static void main(String[] args) {
        System.out.println();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getPathToFileString(String fileName){
        return FileManager.getPathToFile(fileName).toString();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    static Path getPathToFile(String fileName){
        return FileManager.getPathToData().resolve(fileName);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    static Path getPathToData(){
        return Paths.get(System.getProperty("user.dir"), "sampledata");
    }

}
