package com.example.emojitranslator;

public class ExampleItem
{
    private String emoji, description;

    public ExampleItem(String emoji, String mText2)
    {
        this.emoji = emoji;
        this.description = mText2;
    }

    public String getEmoji()
    {
        return emoji;
    }

    public String getDescription()
    {
        return description;
    }
}
