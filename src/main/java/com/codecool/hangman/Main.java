package com.codecool.hangman;

import com.codecool.hangman.game.HighScores;
import com.codecool.hangman.ui.PropertiesReader;
import com.codecool.hangman.ui.ConsoleWriter;
import com.codecool.hangman.file.FileHandler;
import com.codecool.hangman.file.TxtFileHandler;

import java.io.IOException;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) throws IOException {
        var cw = new ConsoleWriter();
        cw.setPr(new PropertiesReader("src/main/resources/english.properties"));

        cw.waitForInput("welcomeScreen");

        var hs = new HighScores(
                new TxtFileHandler("src/main/resources/high_scores.txt"));

        FileHandler fh = new TxtFileHandler("src/main/resources/countries_and_capitals.txt");

        new MainMenuHandler(cw, fh, hs).start();
    }
}
