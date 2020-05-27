package com.codecool.hangman;

import com.codecool.hangman.file.FileHandler;
import com.codecool.hangman.game.Game;
import com.codecool.hangman.game.HighScores;
import com.codecool.hangman.ui.ConsoleWriter;

import java.io.IOException;

public class MainMenuHandler {
    private final HighScores hs;
    private boolean isRunning;
    private final FileHandler fh;
    private final ConsoleWriter cw;

    MainMenuHandler(ConsoleWriter cw, FileHandler fh, HighScores hs) {
        isRunning = true;
        this.fh = fh;
        this.cw = cw;
        this.hs = hs;
    }

    private void choseAndExecuteOption() throws IOException {
        switch (cw.getIntInput("menuChoice")) {
            case 1 -> startNewGame();
            case 2 -> showHighScores();
            case 3 -> settings();
            case 4 -> aboutAuthor();
            case 0 -> exit();
            default -> cw.printMessage("noSuchOption");
        }
    }

    /**
     * @throws IOException If an I/O error occurs during input acquisition
     */
    public void start() throws IOException {
        while (isRunning) {
            cw.clearScreen();
            cw.printMessage("mainMenu");

            choseAndExecuteOption();
        }
    }

    private void startNewGame() throws IOException {
        String[] countryAndCapital = fh.getRandomLineFromFile();
        new Game(countryAndCapital[0], countryAndCapital[1], cw, hs)
                .startGame();
    }

    private void showHighScores() throws IOException {
        cw.printMessage("highScore");
        cw.print(hs.toString());
        cw.waitForInput();
    }

    private void settings() throws IOException {
        new SettingsMenuHandler(cw).start();
    }

    private void aboutAuthor() throws IOException {
        cw.clearScreen();
        cw.printMessage("aboutAuthor");
        cw.waitForInput();
    }

    private void exit() {
        isRunning = false;
    }
}
