package com.example.nutriscore;

import org.junit.Test;
import com.example.nutriscore.FileManager;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        System.out.println(FileManager.getPathToFileString("Natrium.txt"));
    }
}