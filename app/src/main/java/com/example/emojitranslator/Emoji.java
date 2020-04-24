package com.example.emojitranslator;

public class Emoji
{
    private String emoji1Female, emoji2Female, emoji3Female, emoji4Female, emoji5Female,
            emoji6Female, emoji1Male, emoji2Male, emoji3Male, emoji4Male, emoji5Male, emoji6Male;
    private String[] description;
    private int descriptionLength;
    private boolean hasMultipleSkin, hasGender;
    private static Settings settings = Settings.instance();

    public Emoji(String emoji1, String emoji2, String emoji3, String emoji4, String emoji5,
                 String emoji6, String[] description, int descriptionLength)
    {
        this.emoji1Male = emoji1;
        this.emoji2Male = emoji2;
        this.emoji3Male = emoji3;
        this.emoji4Male = emoji4;
        this.emoji5Male = emoji5;
        this.emoji6Male = emoji6;
        this.description = description;
        this.descriptionLength = descriptionLength;
        hasMultipleSkin = true;
        hasGender = false;
    }

    public Emoji(String emoji1Female, String emoji2Female, String emoji3Female, String emoji4Female,
                 String emoji5Female, String emoji6Female, String emoji1Male, String emoji2Male,
                 String emoji3Male, String emoji4Male, String emoji5Male, String emoji6Male,
                 String[] description, int descriptionLength)
    {
        this.emoji1Male = emoji1Male;
        this.emoji2Male = emoji2Male;
        this.emoji3Male = emoji3Male;
        this.emoji4Male = emoji4Male;
        this.emoji5Male = emoji5Male;
        this.emoji6Male = emoji6Male;
        this.emoji1Female = emoji1Female;
        this.emoji2Female = emoji2Female;
        this.emoji3Female = emoji3Female;
        this.emoji4Female = emoji4Female;
        this.emoji5Female = emoji5Female;
        this.emoji6Female = emoji6Female;
        this.description = description;
        this.descriptionLength = descriptionLength;
        hasMultipleSkin = true;
        hasGender = true;
    }

    public Emoji(String emoji, String[] description, int descriptionLength)
    {
        this.emoji1Male = emoji;
        this.description = description;
        this.descriptionLength = descriptionLength;
        hasMultipleSkin = false;
        hasGender = false;
    }

    public String getEmoji()
    {
        int skinOption = settings.getSkin();
        boolean isFemale = settings.isFemale();

        if (skinOption == 2 && hasMultipleSkin)
        {
            if (hasGender && isFemale)
                return emoji2Female;

            else
                return emoji2Male;
        }

        else if (skinOption == 3 && hasMultipleSkin)
        {
            if (hasGender && isFemale)
                return emoji3Female;

            else
                return emoji3Male;
        }

        else if (skinOption == 4 && hasMultipleSkin)
        {
            if (hasGender && isFemale)
                return emoji4Female;

            else
                return emoji4Male;
        }

        else if (skinOption == 5 && hasMultipleSkin)
        {
            if (hasGender && isFemale)
                return emoji5Female;

            else
                return emoji5Male;
        }

        else if (skinOption == 6 && hasMultipleSkin)
        {
            if (hasGender && isFemale)
                return emoji6Female;

            else
                return emoji6Male;
        }

        else
        {
            if (hasGender && isFemale)
                return emoji1Female;

            else
                return emoji1Male;
        }
    }

    public String[] getDescription()
    {
        return description;
    }

    public int getDescriptionLength()
    {
        return descriptionLength;
    }

    public String toString()
    {
        return emoji1Male;
    }
}
