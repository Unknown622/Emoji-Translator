package com.example.emojitranslator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View;


public class SettingsActivity extends AppCompatActivity
{
    private RadioGroup translationOptions, genderOptions, skinOptions;
    private RadioButton classic, replace, male, female, skin1, skin2, skin3, skin4, skin5, skin6;
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings = Settings.instance();
        translationOptions = findViewById(R.id.radioGroupTranslationOptions);
        genderOptions = findViewById(R.id.genderOptions);
        skinOptions = findViewById(R.id.skinColorOptions);
        classic = findViewById(R.id.translationOptionClassic);
        replace = findViewById(R.id.translationOptionReplace);
        male = findViewById(R.id.genderOptionMale);
        female = findViewById(R.id.genderOptionFemale);
        skin1 = findViewById(R.id.skinOption1);
        skin2 = findViewById(R.id.skinOption2);
        skin3 = findViewById(R.id.skinOption3);
        skin4 = findViewById(R.id.skinOption4);
        skin5 = findViewById(R.id.skinOption5);
        skin6 = findViewById(R.id.skinOption6);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSettings();
    }

    public void checkSkinColor(View v)
    {
        if (skinOptions.getCheckedRadioButtonId() == skin2.getId())
        {
            settings.setSkin(2);
        }

        else if (skinOptions.getCheckedRadioButtonId() == skin3.getId())
        {
            settings.setSkin(3);
        }

        else if (skinOptions.getCheckedRadioButtonId() == skin4.getId())
        {
            settings.setSkin(4);
        }

        else if (skinOptions.getCheckedRadioButtonId() == skin5.getId())
        {
            settings.setSkin(5);
        }

        else if (skinOptions.getCheckedRadioButtonId() == skin6.getId())
        {
            settings.setSkin(6);
        }

        else
        {
            settings.setSkin(1);
        }
    }

    public void checkTranslation(View v)
    {
        if (translationOptions.getCheckedRadioButtonId() == replace.getId())
        {
            settings.setReplace(true);
        }

        else
        {
            settings.setClassic(true);
        }
    }

    public void checkGender(View v)
    {
        if (genderOptions.getCheckedRadioButtonId() == female.getId())
        {
            settings.setFemale(true);
        }

        else
        {
            settings.setMale(true);
        }
    }

    private void setSettings()
    {
        classic.setChecked(settings.isClassic());
        replace.setChecked(settings.isReplace());
        female.setChecked(settings.isFemale());
        male.setChecked(settings.isMale());

        int skin = settings.getSkin();

        if (skin == 2)
        {
            skin2.setChecked(true);
        }

        else if (skin == 3)
        {
            skin3.setChecked(true);
        }

        else if (skin == 4)
        {
            skin4.setChecked(true);
        }

        else if (skin == 5)
        {
            skin5.setChecked(true);
        }

        else if (skin == 6)
        {
            skin6.setChecked(true);
        }

        else
        {
            skin1.setChecked(true);
        }
    }
}
