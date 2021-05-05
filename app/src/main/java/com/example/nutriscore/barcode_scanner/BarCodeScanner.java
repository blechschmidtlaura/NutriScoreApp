package com.example.nutriscore.barcode_scanner;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutriscore.calculation.ElasticsearchHandler;
import com.example.nutriscore.calculation.Food;
import com.example.nutriscore.calculation.NutriScore;
import com.example.nutriscore.calculation.Product;
import com.example.nutriscore.information.InformationNutriScore;
import com.example.nutriscore.result_view.NutriScoreResult;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutriscore.R;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import org.json.JSONException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 *  BarCodeScanner - scant mit der Kamera den Barcode -> dann NutriScore
 */
public class BarCodeScanner extends AppCompatActivity {

    Button informationButton; //bei Klicken gelangt man zu einer Informationsseite
    TextView barcodeInfo; // barcodeInfo zeigt den von der Kamera erkannten Barcode an!
    SurfaceView cameraView; // cameraView zeigt, das aktuell von der Kamera erfasste Bild
    CameraSource cameraSource; // cameraSource -> ist die Kammera, welche die Bilder liefert
    private static final int REQUEST_CAMERA_PERMISSION = 201; // REQUEST_CAMERA_PERMISSION ist der REQUEST Code für die Kamera Berechtigung
    @SuppressLint("StaticFieldLeak")
    private static BarCodeScanner instance; // diese KLasse ist ein Singleton, es gibt immer nur eine Instanz der Klasse
    Button manuellBarcode;

    /**
     * OnCreate Funktion
     * wird beim erstellen der activity aufgerufen
     * es werden die Elemete der Activity inistialisiert
     * und das Kamera Bild wird aufgezeichnet
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code_scanner); // Initializiert das Layout des Bildschirms
        instance = this; // ermöglich den Zugriff aus anderen Klassen auf diese Instanz
        cameraView = (SurfaceView) findViewById(R.id.camera_view); // Bilder der Kamera
        barcodeInfo = (TextView) findViewById(R.id.barCode); // Erkannter Barcode
        manuellBarcode = (Button) findViewById(R.id.eingabeBarcodeButton);

        startCamera(); // startet die Kamera!
        this.manuellBarcode.setOnClickListener(this:: changeToNutriScoreResult);

        informationButton = (Button) findViewById(R.id.information_button);
        this.informationButton.setOnClickListener(this::changeInformationNutriScore);
    }


    /**
     * Die Methode startCamera, startet die Kamera
     * Die gelieferten Bilder der Kamera werden nach Barcodes durchsucht
     */
    protected void startCamera() {
        BarcodeDetector barcodeDetector =
                new BarcodeDetector.Builder(this)
                        .setBarcodeFormats(Barcode.EAN_13) // QR_CODE vom Typ EAN_13
                        .build();

        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setAutoFocusEnabled(true) // Auto fokussieren der Kamera, verbessert die Erkennung
                .setFacing(CameraSource.CAMERA_FACING_BACK) // Die Hintere Kamera, man könnte auch die Selfie Kamera verwenden
                .setRequestedPreviewSize(3840, 2160) // Die Größe der Kamera Bilder 3840*2160 entspricht 4K
                .build();

        SurfaceHolder holder = cameraView.getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    // Abfrage nach der Erlaubnis der Kamera
                    if (ActivityCompat.checkSelfPermission(BarCodeScanner.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(cameraView.getHolder());
                    } else {
                        // Kamera Berechtigungs Anfrage
                        ActivityCompat.requestPermissions(BarCodeScanner.this, new
                                String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                        this.surfaceCreated(holder);
                    }
                } catch (IOException ie) {
                    Log.e("CAMERA SOURCE", "LOOOOL" + ie.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        // definiert was passieren soll ven ein Barcode entdeckt wurde !
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0) {
                    barcodeInfo.post(new Runnable() {    // Use the post method of the TextView
                        public void run() {
                            barcodeInfo.setText(    // Update the TextView
                                    barcodes.valueAt(0).displayValue
                            );

                        }
                    });
                    String ean = barcodes.valueAt(0).rawValue;
                    BarCodeScanner.changeToResult(instance, ean);
                }
            }
        });

    }

    /**
     * Wenn ein Barcode erkannt wurde wird zur Result Activity gewechselt
     * @param a die jetzige BarcodeActivity
     * @param ean Die ean des entdeckten Barcodes
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected static void changeToResult(Activity a, String ean){
        final List<Optional<Product>> food = new LinkedList<>();
        final Context c = a.getApplicationContext();
        // Die ElasticSearch Request wird auf einem anderen Thread ausgeführt
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() throws IllegalArgumentException{
                try {
                    food.add(ElasticsearchHandler.getFoodByEAN(ean, c));
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Optional<Product> optionalFood = food.get(0);
        Product f;
        // Wenn ein Nahrungsmittel entdeckt wurde, wird zur NutriScoreResult Activity gewechselt
        if(optionalFood.isPresent()){
            f = optionalFood.get();
            Intent intent = new Intent(a, NutriScoreResult.class);
            // die Nahrungsmittelinstanz food wird an die neue Activity übertragen!
            intent.putExtra("food", f);
            a.startActivity(intent);
        }


    }

    protected void changeToNutriScoreResult(View v){
        Intent intent = new Intent(this, NutriScoreResult.class);
        startActivity(intent);
    }

    protected void changeInformationNutriScore(View v){
        Intent intent = new Intent(this, InformationNutriScore.class);
        startActivity(intent);
    }
}
