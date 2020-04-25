package com.example.emojitranslator;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.view.View;
import android.widget.*;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Vector;
import android.app.Activity;
import android.view.inputmethod.*;

public class MainActivity extends AppCompatActivity
{
    private EditText output, input;
    private EmojiList emojis = EmojiList.instance();

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
                hideKeyboard();
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

    public void openSearch(View v)
    {
        Intent intent = new Intent(this, SearchActivity.class);
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
            Toast.makeText(getApplicationContext(), "Sorry, your device doesn't support this.", Toast.LENGTH_SHORT).show();
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
                    translate();
                }
                break;
        }
    }

    public void hideKeyboard()
    {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void translate()
    {
        if (Settings.instance().isClassic())
            translateClassic();

        else
            translateReplace();
    }

    private void translateReplace()
    {
        Editable inputEditable = input.getText();
        String inputText = inputEditable.toString();
        Vector<String> vector = stringToVector(inputText);

        for (int i = 0; i < vector.size(); ++i)
        {
            String emoji = emojis.searchEmojis(vector.get(i));

            if (emoji != null)
                vector.set(i, emoji);
        }

        output.setText(vectorToString(vector));
    }

    private void translateClassic()
    {
        Editable inputEditable = input.getText();
        String inputText = inputEditable.toString();
        Vector<String> vector = stringToVector(inputText);

        for (int i = 0; i < vector.size(); ++i)
        {
            if (Character.isLetter(vector.get(i).charAt(0)) ||
                    Character.isDigit(vector.get(i).charAt(0)))
            {
                String emoji = emojis.searchEmojis(vector.get(i));

                if (emoji != null)
                {
                    vector.insertElementAt(emoji, i + 1);
                    ++i;
                }
            }
        }

        output.setText(vectorToString(vector));
    }

    private String vectorToString(Vector<String> vector)
    {
        String outputString = "";

        for (int i = 0; i < vector.size(); ++i)
        {
            outputString += vector.elementAt(i);
        }

        return outputString;
    }

    private Vector<String> stringToVector(String string)
    {
        String word = "";
        Vector<String> vector = new Vector<>();

        for (int i = 0; i < string.length(); ++i)
        {
            if (Character.isLetter(string.charAt(i)) || Character.isDigit(string.charAt(i)) ||
                string.charAt(i) == '-')
                word += string.charAt(i);

            else
            {
                if (!word.equals(""))
                {
                    vector.add(word);
                    word = "";
                }

                vector.add(Character.toString(string.charAt(i)));
            }
        }

        if (!word.equals(""))
        {
            vector.add(word);
        }

        return vector;
    }
}
