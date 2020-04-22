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

    private void add(String emoji1, String emoji2, String emoji3, String emoji4, String emoji5,
                    String emoji6, String[] description, int descriptionLength)
    {
        emojis.add(new Emoji(emoji1, emoji2, emoji3, emoji4, emoji5, emoji6, description,
                descriptionLength));
    }

    private void add(String emoji1Female, String emoji2Female, String emoji3Female, String emoji4Female,
                 String emoji5Female, String emoji6Female, String emoji1Male, String emoji2Male,
                 String emoji3Male, String emoji4Male, String emoji5Male, String emoji6Male,
                 String[] description, int descriptionLength)
    {
        emojis.add(new Emoji(emoji1Female, emoji2Female, emoji3Female, emoji4Female, emoji5Female,
                emoji6Female, emoji1Male, emoji2Male, emoji3Male, emoji4Male, emoji5Male, emoji6Male,
                description, descriptionLength));
    }

    private void add(String emoji, String[] description, int descriptionLength)
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
        add("👍", "👍🏻", "👍🏼", "👍🏽", "👍🏾",
                "👍🏿", new String[] {"good"}, 1);

        add("👋", "👋🏻", "👋🏼", "👋🏽", "👋🏾",
                "👋🏿", new String[] {"hello", "goodbye", "hi"}, 3);

        add("🤷‍♀️", "🤷🏻‍♀️", "🤷🏼‍♀️", "🤷🏽‍♀️",
                "🤷🏾‍♀️", "🤷🏿‍♀️", "🤷‍♂️", "🤷🏻‍♂️",
                "🤷🏼‍♂️", "🤷🏽‍♂️", "🤷🏾‍♂️", "🤷🏿‍♂️",
                new String[] {"oop", "oops", "shrug", "shrugging"}, 4);

        add("😂", new String[] {"lol", "lmao"}, 2);

        add("🏠", new String[] {"house", "home"}, 2);

        add("😀", new String[] {"happy", "grin", "grinning", "smiling", "smile"},
                5);

        add("\uD83D\uDE03", new String[] {"happy", "grin", "grinning", "smiling", "smile"},
                5);

        add("\uD83D\uDE04", new String[] {"happy", "grin", "grinning", "smiling", "smile"},
                5);

        add("\uD83D\uDE01", new String[] {"happy", "grin", "grinning", "smiling", "smile"},
                5);

        add("\uD83D\uDE06", new String[] {"happy", "grin", "grinning", "smiling", "smile",
                "laugh", "laughing"}, 7);

        add("\uD83D\uDE05", new String[] {"embarrassed", "embarrassing", "whew", "nervous"},
                4);

        add("\uD83E\uDD23", new String[] {"lmfao", "rofl", "laugh", "laughing"},
                2);

        add("\uD83D\uDE42", new String [] {"fine", "patronizing", "patronize"},
                1);

        add("\uD83D\uDE43", new String[] {"silly", "sarcastic", "goofy", "ironic"},
                4);

        add("\uD83D\uDE09", new String[] {"ironic", "playful", "wink", "winking", "joke",
                "joking"}, 6);

        add("\uD83D\uDE0A", new String[] {"happy", "happiness", "genuine", "smile", "smiling"},
                5);

        add("\uD83D\uDE07", new String[] {"angel", "angelic", "blessing", "halo"},
                4);

        add("\uD83E\uDD70", new String[] {"love", "affection", "affectionate"}, 3);

        add("\uD83D\uDE0D", new String[] {"love", "infatuation", "infatuated", "adore",
                "adoration"}, 5);

        add("\uD83E\uDD29", new String[] {"excited", "excitement", "amazement", "amazed",
                        "wow", "starstruck", "star-struck"}, 5);

        add("\uD83D\uDE18", new String[] {"kiss", "kissing", "love"}, 3);

        add("\uD83D\uDE17", new String[] {"whistle", "whistling", "kissing", "surprise",
                "surprising"}, 5);

        add("☺️", new String[] {"warm", "positive", "grateful", "love"}, 4);

        add("\uD83D\uDE1A", new String[] {"kiss", "kissing", "kissy"}, 3);
    }
}
