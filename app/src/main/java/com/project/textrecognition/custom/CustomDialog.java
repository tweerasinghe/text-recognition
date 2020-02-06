package com.project.textrecognition.custom;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.project.textrecognition.R;
import com.project.textrecognition.ui.ResultActivity;

public class CustomDialog extends Dialog {

    public Context ctx;
    private String englishText;

    private ListView listView;
    String[] languages = new String[]{
            "Afrikaans", "Arabic", "Belarusian", "Bulgarian", "Bengali", "Catalan", "Czech", "Welsh", "Danish", "German", "Greek", "English", "Esperanto",
            "Spanish", "Estonian", "Persian", "Finnish", "French", "Irish", "Galician", "Gujarati", "Hebrew", "Hindi", "Croatian", "Haitian", "Hungarian",
            "Indonesian", "Icelandic", "Italian", "Japanese", "Georgian", "Kannada", "Korean", "Lithuanian", "Latvian", "Macedonian", "Marathi", "Malay",
            "Maltese", "Dutch", "Norwegian", "Polish", "Portuguese", "Romanian", "Russian", "Slovak", "Slovenian", "Albanian", "Swedish", "Swahili",
            "Tamil", "Telugu", "Thai", "Tagalog", "Turkish", "Ukranian", "Urdu", "Vietnamese"};


    public CustomDialog(Context ctx, String englishText) {
        super(ctx);
        this.ctx = ctx;
        this.englishText = englishText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);

        initializeView();

        loadDataIntoListView(languages);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedLanguage = listView.getItemAtPosition(position).toString();

                int langCode = getLanguageCode(selectedLanguage);

                Intent intent = new Intent(ctx.getApplicationContext(), ResultActivity.class);
                intent.putExtra("langCode",langCode);
                intent.putExtra("englishText",englishText);

                ctx.startActivity(intent);

                dismiss();
            }
        });


    }

    /**
     * initialize views in custom_dialog.xml
     */
    private void initializeView() {
        listView = findViewById(R.id.languageListView);
    }

    private void loadDataIntoListView(String[] languages) {
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_list_item_1, languages);


        listView.setAdapter(arrayAdapter);
    }

    /**
     * get language code using language text
     *
     * @param language
     * @return
     */
    private static int getLanguageCode(String language) {
        int languageID = 0;

        switch (language) {

            case "Arabic":
                languageID = FirebaseTranslateLanguage.AR;
                break;
            case "Belarusian":
                languageID = FirebaseTranslateLanguage.BE;
                break;
            case "Bulgarian":
                languageID = FirebaseTranslateLanguage.BG;
                break;

            case "Bengali":
                languageID = FirebaseTranslateLanguage.BN;
                break;
            case "Catalan":
                languageID = FirebaseTranslateLanguage.CA;
                break;
            case "Czech":
                languageID = FirebaseTranslateLanguage.CS;
                break;
            case "Welsh":
                languageID = FirebaseTranslateLanguage.CY;
                break;
            case "Danish":
                languageID = FirebaseTranslateLanguage.DA;
                break;
            case "German":
                languageID = FirebaseTranslateLanguage.DE;
                break;
            case "Greek":
                languageID = FirebaseTranslateLanguage.EL;
                break;
            case "English":
                languageID = FirebaseTranslateLanguage.EN;
                break;
            case "Esperanto":
                languageID = FirebaseTranslateLanguage.EO;
                break;
            case "Spanish":
                languageID = FirebaseTranslateLanguage.ES;
                break;
            case "Estonian":
                languageID = FirebaseTranslateLanguage.ET;
                break;
            case "Persian":
                languageID = FirebaseTranslateLanguage.FA;
                break;
            case "Finnish":
                languageID = FirebaseTranslateLanguage.FI;
                break;
            case "French":
                languageID = FirebaseTranslateLanguage.FR;
                break;
            case "Irish":
                languageID = FirebaseTranslateLanguage.GA;
                break;
            case "Galician":
                languageID = FirebaseTranslateLanguage.GL;
                break;
            case "Gujarati":
                languageID = FirebaseTranslateLanguage.GU;
                break;
            case "Hebrew":
                languageID = FirebaseTranslateLanguage.HE;
                break;
            case "Hindi":
                languageID = FirebaseTranslateLanguage.HI;
                break;
            case "Croatian":
                languageID = FirebaseTranslateLanguage.HR;
                break;
            case "Haitian":
                languageID = FirebaseTranslateLanguage.HT;
                break;
            case "Hungarian":
                languageID = FirebaseTranslateLanguage.HU;
                break;
            case "Indonesian":
                languageID = FirebaseTranslateLanguage.ID;
                break;
            case "Icelandic":
                languageID = FirebaseTranslateLanguage.IS;
                break;
            case "Italian":
                languageID = FirebaseTranslateLanguage.IT;
                break;
            case "Japanese":
                languageID = FirebaseTranslateLanguage.JA;
                break;
            case "Georgian":
                languageID = FirebaseTranslateLanguage.KA;
                break;
            case "Kannada":
                languageID = FirebaseTranslateLanguage.KN;
                break;
            case "Korean":
                languageID = FirebaseTranslateLanguage.KO;
                break;
            case "Lithuanian":
                languageID = FirebaseTranslateLanguage.LT;
                break;
            case "Latvian":
                languageID = FirebaseTranslateLanguage.LV;
                break;
            case "Macedonian":
                languageID = FirebaseTranslateLanguage.MK;
                break;
            case "Marathi":
                languageID = FirebaseTranslateLanguage.MR;
                break;
            case "Malay":
                languageID = FirebaseTranslateLanguage.MS;
                break;
            case "Maltese":
                languageID = FirebaseTranslateLanguage.MT;
                break;
            case "Dutch":
                languageID = FirebaseTranslateLanguage.NL;
                break;
            case "Norwegian":
                languageID = FirebaseTranslateLanguage.NO;
                break;
            case "Polish":
                languageID = FirebaseTranslateLanguage.PL;
                break;
            case "Portuguese":
                languageID = FirebaseTranslateLanguage.PT;
                break;
            case "Romanian":
                languageID = FirebaseTranslateLanguage.RO;
                break;
            case "Russian":
                languageID = FirebaseTranslateLanguage.RU;
                break;
            case "Slovak":
                languageID = FirebaseTranslateLanguage.SK;
                break;
            case "Slovenian":
                languageID = FirebaseTranslateLanguage.SL;
                break;
            case "Albanian":
                languageID = FirebaseTranslateLanguage.SQ;
                break;
            case "Swedish":
                languageID = FirebaseTranslateLanguage.SV;
                break;
            case "Swahili":
                languageID = FirebaseTranslateLanguage.SW;
                break;
            case "Tamil":
                languageID = FirebaseTranslateLanguage.TA;
                break;
            case "Telugu":
                languageID = FirebaseTranslateLanguage.TE;
                break;
            case "Thai":
                languageID = FirebaseTranslateLanguage.TH;
                break;
            case "Tagalog":
                languageID = FirebaseTranslateLanguage.TL;
                break;
            case "Turkish":
                languageID = FirebaseTranslateLanguage.TR;
                break;
            case "Ukranian":
                languageID = FirebaseTranslateLanguage.UK;
                break;
            case "Urdu":
                languageID = FirebaseTranslateLanguage.UR;
                break;
            case "Vietnamese":
                languageID = FirebaseTranslateLanguage.VI;
                break;


        }

        return languageID;
    }
}
