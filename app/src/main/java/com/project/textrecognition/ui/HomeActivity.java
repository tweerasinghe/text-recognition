package com.project.textrecognition.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.project.textrecognition.R;

public class HomeActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private CardView scan, translate;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ctx = this;

        initializeView();


        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTranslateIntent(null);
            }
        });
    }

    /**
     * start translate activity
     *
     * @param identifiedText
     */
    private void startTranslateIntent(String identifiedText) {


        Intent translateIntent = new Intent(HomeActivity.this, TranslateActivity.class);

        if (identifiedText != null) {
            translateIntent.putExtra("identifiedText", identifiedText);
        }

        startActivity(translateIntent);
    }

    /**
     * capture and convert into bitmap
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            detextTextFromImage(imageBitmap);

        }
    }

    /**
     * get text from bitmap image
     *
     * @param imageBitmap pass text bitmap
     */
    private void detextTextFromImage(Bitmap imageBitmap) {


        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(imageBitmap);

        FirebaseVisionTextRecognizer firebaseVisionTextRecognizer = FirebaseVision.getInstance().getCloudTextRecognizer();

        firebaseVisionTextRecognizer.processImage(image)
                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                    @Override
                    public void onSuccess(FirebaseVisionText firebaseVisionText) {
                        String text = firebaseVisionText.getText();

                        if (text.isEmpty() || text == null)
                            Toast.makeText(ctx, "Can not identify. Try again!", Toast.LENGTH_SHORT).show();

                        else {

                            startTranslateIntent(text);
                        }
                    }
                })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                e.printStackTrace();
                            }
                        });


    }


    /**
     * request camera intent
     */
    private void dispatchTakePictureIntent() {


        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * initialize views
     */
    private void initializeView() {
        scan = findViewById(R.id.scanCardView);
        translate = findViewById(R.id.translateCardView);
    }
}
