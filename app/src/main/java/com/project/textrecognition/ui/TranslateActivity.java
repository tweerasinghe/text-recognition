package com.project.textrecognition.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import androidx.appcompat.app.AppCompatActivity;

import com.project.textrecognition.custom.CustomDialog;
import com.project.textrecognition.R;


public class TranslateActivity extends AppCompatActivity {

    private Context ctx;
    private EditText textEdtText;
    private Button translateBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        ctx = this;

        initializeView();


        getSupportActionBar().setTitle("Text Translate");



        final String translateText = getIntent().getStringExtra("identifiedText");
        if (translateText != null) {
            textEdtText.setText(translateText);
        }


        translateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textEdtText.getText().toString();

                if (text.isEmpty())
                    Toast.makeText(ctx, "Please Enter Text", Toast.LENGTH_SHORT).show();
                else {
                    CustomDialog cdd = new CustomDialog(ctx,text);
                    cdd.show();


                }
            }
        });

    }


    /**
     * initialize views
     */
    private void initializeView() {
        textEdtText = findViewById(R.id.textEditText);
        translateBtn = findViewById(R.id.textTranslate);
    }


}
