package com.codecool.hangman;

import com.codecool.hangman.ui.ConsoleWriter;

import java.io.IOException;

public class SettingsMenuHandler {
    boolean isRunning;
    final ConsoleWriter cw;

    SettingsMenuHandler(ConsoleWriter cw) {
        isRunning = true;
        this.cw = cw;
    }

    private void choseAndExecuteOption() throws IOException {
        switch (cw.getIntInput("menuChoice")) {
            case 1 -> changeLanguage();
            case 2 -> changeStartingHp();
            case 0 -> back();
            default -> cw.printMessage("noSuchOption");
        }
    }

    /**
     * @throws IOException If an I/O error occurs during input acquisition
     */
    public void start() throws IOException {
        while (isRunning) {
            cw.clearScreen();
            cw.printMessage("settingsMenu");

            choseAndExecuteOption();
        }
    }

    private void changeLanguage() throws IOException {
        int languageInt = cw.getIntInputInRange(1, 2, "setLanguage");
        String language = languageInt == 1 ? "english" : "polish";
        Settings.INSTANCE.setLanguage(language);
        cw.setLanguage(Settings.INSTANCE.getLanguage());
    }

    private void changeStartingHp() throws IOException {
        int hp = cw.getIntInputInRange(1, 1000, "setHp");
        Settings.INSTANCE.setMaxHp(hp);
    }

    private void back() {
        isRunning = false;
    }
}
