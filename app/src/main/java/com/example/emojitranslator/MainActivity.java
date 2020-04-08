package com.example.emojitranslator;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{
    private EditText output, input;
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button settingsButton, copyButton, clearButton, translate;

        settings = Settings.instance();
        output = findViewById(R.id.outputText);
        input = findViewById(R.id.inputText);
        copyButton = findViewById(R.id.btnCopy);
        settingsButton = findViewById(R.id.btnSettings);
        clearButton = findViewById(R.id.btnClear);
        translate = findViewById(R.id.btnTranslate);
        settings = Settings.instance();

        clearButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                input.setText("");
                output.setText("");
            }
        });

        translate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                translate();
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openSettings();
            }
        });

        copyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Translation", output.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getApplicationContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openSettings()
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void translate()
    {
        output.setText(input.getText());
    }
}
