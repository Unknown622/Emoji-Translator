package com.example.emojitranslator;

public class Settings
{
    private boolean classic, replace, male, female;
    private int skin;
    private static Settings settings;

    private Settings()
    {
        classic = true;
        replace = false;
        male = true;
        female = false;
        skin = 1;
    }

    public static Settings instance()
    {
        if (settings == null)
        {
            settings = new Settings();
            return settings;
        }

        return settings;
    }

    public boolean isClassic()
    {
        return classic;
    }

    public boolean isReplace()
    {
        return replace;
    }

    public boolean isMale()
    {
        return male;
    }

    public boolean isFemale()
    {
        return female;
    }

    public int getSkin()
    {
        return skin;
    }

    public void setClassic(boolean input)
    {
        classic = input;
        replace = !input;
    }

    public void setReplace(boolean input)
    {
        replace = input;
        classic = !input;
    }

    public void setMale(boolean input)
    {
        male = input;
        female = !input;
    }

    public void setFemale(boolean input)
    {
        female = input;
        male = !input;
    }

    public void setSkin(int input)
    {
        if (input >= 1 && input <= 6)
            skin = input;
    }
}
