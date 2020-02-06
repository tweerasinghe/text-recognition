package com.project.textrecognition.ui;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;
import com.project.textrecognition.R;

public class ResultActivity extends AppCompatActivity {

    private EditText resultText;
    private Button copyText;
    private String englishText;
    private int langCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initializeView();

        getSupportActionBar().setTitle("Translated Text");


        getDataFromIntent();


        translateText(langCode, englishText);


        copyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = resultText.getText().toString();
                if (text.isEmpty())
                    Toast.makeText(ResultActivity.this, "No text to copy", Toast.LENGTH_SHORT).show();
                else {
                    copyTextAction(text);
                }
            }
        });


    }

    private void copyTextAction(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("translatedText", text);
        clipboard.setPrimaryClip(clip);

        Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show();
    }


    /**
     * get langCode and englishText from previous intent
     */
    private void getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        englishText = bundle.getString("englishText");
        langCode = bundle.getInt("langCode");
    }

    /**
     * initialize activity views
     */
    private void initializeView() {
        resultText = findViewById(R.id.translatedText);
        copyText = findViewById(R.id.copyTxtBtn);
    }

    /**
     * translate text into specific language
     *
     * @param targetLanguage translate language id
     */
    private void translateText(int targetLanguage, final String text) {

        final ProgressDialog dialog = ProgressDialog.show(ResultActivity.this, "Text Translating",
                "Translating. Please wait...");

        dialog.show();

        FirebaseTranslatorOptions options =
                new FirebaseTranslatorOptions.Builder()
                        .setSourceLanguage(FirebaseTranslateLanguage.EN)
                        .setTargetLanguage(targetLanguage)
                        .build();

        final FirebaseTranslator textTranslator =
                FirebaseNaturalLanguage.getInstance().getTranslator(options);


        FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder()
                .requireWifi()
                .build();
        textTranslator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener(
                        new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void v) {
                                textTranslator.translate(text)
                                        .addOnSuccessListener(
                                                new OnSuccessListener<String>() {
                                                    @Override
                                                    public void onSuccess(@NonNull String translatedText) {
                                                        dialog.dismiss();
                                                        resultText.setText(translatedText);

                                                        Log.e("text", translatedText);
                                                    }
                                                })
                                        .addOnFailureListener(
                                                new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        // Error.
                                                        // ...
                                                    }
                                                });
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
    }
}
