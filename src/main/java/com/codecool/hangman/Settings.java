package com.codecool.hangman;

import java.util.prefs.*;

public enum Settings {
    INSTANCE;

    private final Preferences prefs = Preferences.userNodeForPackage(Settings.class);
    private final String LANGUAGE = "Language";
    private final String MAX_HP = "Starting hp";

    void setLanguage(String languagePath) {
        prefs.put(LANGUAGE, languagePath);
    }

    String getLanguage() {
        return prefs.get(LANGUAGE, "english");
    }

    void setMaxHp(int maxHp) {
        prefs.putInt(MAX_HP, maxHp);
    }

    public int getMaxHp() {
        return prefs.getInt(MAX_HP, 5);
    }
}
