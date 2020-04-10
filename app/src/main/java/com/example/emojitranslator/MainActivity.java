package com.example.emojitranslator;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.*;
import android.os.Bundle;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private EditText output, input;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button copyButton, clearButton, translate;

        output = findViewById(R.id.outputText);
        input = findViewById(R.id.inputText);
        copyButton = findViewById(R.id.btnCopy);
        clearButton = findViewById(R.id.btnClear);
        translate = findViewById(R.id.btnTranslate);

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

        copyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Translation", output.getText().toString());
                clipboard.setPrimaryClip(clip);

                //Show confirmation message
                Toast.makeText(getApplicationContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openSettings(View v)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void speechToText(View v)
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(intent, 10);
        }

        else
        {
            //Shows error message
            Toast.makeText(getApplicationContext(), "Sorry, your device doesn't support this", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case 10:
                if (resultCode == RESULT_OK && data != null)
                {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    input.setText(result.get(0));
                }
                break;
        }
    }

    public void translate()
    {
        output.setText(input.getText());
    }
}
