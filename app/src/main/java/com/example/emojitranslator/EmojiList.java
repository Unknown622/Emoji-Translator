package com.example.emojitranslator;
import java.util.LinkedList;
import java.util.Iterator;

public class EmojiList
{
    private static EmojiList instance;
    private LinkedList<Emoji> emojis;

    private EmojiList()
    {
        emojis = new LinkedList<>();
        populate();
    }

    public static EmojiList instance()
    {
        if (instance == null)
            return instance = new EmojiList();

        else
            return instance;
    }

    public void add(String emoji1, String emoji2, String emoji3, String emoji4, String emoji5,
                    String emoji6, String[] description, int descriptionLength)
    {
        emojis.add(new Emoji(emoji1, emoji2, emoji3, emoji4, emoji5, emoji6, description,
                descriptionLength));
    }

    public void add(String emoji1Female, String emoji2Female, String emoji3Female, String emoji4Female,
                 String emoji5Female, String emoji6Female, String emoji1Male, String emoji2Male,
                 String emoji3Male, String emoji4Male, String emoji5Male, String emoji6Male,
                 String[] description, int descriptionLength)
    {
        emojis.add(new Emoji(emoji1Female, emoji2Female, emoji3Female, emoji4Female, emoji5Female,
                emoji6Female, emoji1Male, emoji2Male, emoji3Male, emoji4Male, emoji5Male, emoji6Male,
                description, descriptionLength));
    }

    public void add(String emoji, String[] description, int descriptionLength)
    {
        emojis.add(new Emoji(emoji, description, descriptionLength));
    }

    public String searchEmojis(String word)
    {
        Iterator iterator = emojis.iterator();

        while (iterator.hasNext())
        {
            Emoji emoji = (Emoji) iterator.next();

            String[] description = emoji.getDescription();

            for (int i = 0; i < emoji.getDescriptionLength(); ++i)
            {
                if (description[i].equalsIgnoreCase(word))
                    return emoji.getEmoji();
            }
        }

        return null;
    }

    public Iterator<Emoji> getEmojis()
    {
        return emojis.iterator();
    }

    private void populate()
    {
        String[] description = {"good"};
        add("👍", "👍🏻", "👍🏼", "👍🏽", "👍🏾",
                "👍🏿", description, 1);

        String[] description1 = {"hello", "goodbye", "hi"};
        add("👋", "👋🏻", "👋🏼", "👋🏽", "👋🏾",
                "👋🏿", description1, 3);

        String[] description2 = {"oop", "oops", "shrug", "shrugging", "meh"};
        add("🤷‍♀️", "🤷🏻‍♀️", "🤷🏼‍♀️", "🤷🏽‍♀️",
                "🤷🏾‍♀️", "🤷🏿‍♀️", "🤷‍♂️", "🤷🏻‍♂️",
                "🤷🏼‍♂️", "🤷🏽‍♂️", "🤷🏾‍♂️", "🤷🏿‍♂️",
                description2, 5);

        String[] description3 = {"lol", "lmao"};
        add("😂", description3, 2);

        String[] description4 = {"house", "home"};
        add("🏠", description4, 2);
    }
}
