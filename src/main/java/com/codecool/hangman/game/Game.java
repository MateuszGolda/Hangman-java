package com.codecool.hangman.game;

import com.codecool.hangman.Settings;
import com.codecool.hangman.ui.ConsoleWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Game {
    private static final byte WRONG_WORD_DAMAGE = 2;
    private static final byte WRONG_LETTER_DAMAGE = 1;
    private static final short STARTING_POINTS = 1000;
    private final ConsoleWriter cw;
    private final String country;
    private final String capital;
    private final Set<String> notGuessedLetters;
    private final Set<String> guessedLetters;
    private final Set<String> notInWord;
    private final Player player;
    private final GameDisplay gd;
    private final HighScores hs;
    private long startTime;
    private long endTime;

    public Game(String country, String capital, ConsoleWriter cw, HighScores hs) throws IOException {
        this.cw = cw;
        this.country = country;
        this.capital = capital;
        this.hs = hs;
        notGuessedLetters = new HashSet<>();
        guessedLetters = new HashSet<>();
        notInWord = new HashSet<>();
        player = new Player(Settings.INSTANCE.getMaxHp());
        cw.printMessage("whatsYourName");
        player.setName(cw.getInput());
        gd = new GameDisplay(player, this, cw);
    }

    public void startGame() throws IOException {
        initializeNotGuessedLetters();
        startTime = System.currentTimeMillis();
        while (!isGameOver()) {
            cw.clearScreen();
            cw.print(gd.toString());
            cw.printMessage("enterLetterOrWord");
            handleGuess(cw.getInput().toLowerCase());
            cw.waitForInput();
        }
        endTime = System.currentTimeMillis();
        cw.clearScreen();
        cw.print(gd.toString());
        handleGameOver();
        cw.waitForInput();
    }

    private void handleGameOver() throws FileNotFoundException {
        if (isGameLost()) {
            handleLostGame();
        } else if (isGameWon()) {
            handleWonGame();
        }
    }

    private void handleWonGame() throws FileNotFoundException {
        cw.printMessage("gameWon");
        int score = calculateScore();
        var addedToHighScores = hs.addToHighScores(player.getName(), score);
        if (addedToHighScores) {
            cw.printMessage("addedToHighScores");
            hs.save();
        }
    }

    private void handleLostGame() {
        cw.printMessage("gameLost");
        cw.print(String.format(
                cw.getMessage("countryCapital"), country, capital));
    }

    private int calculateScore() {
        int secondsElapsed = (int) ((endTime - startTime) / 1000);
        int hpLost = Settings.INSTANCE.getMaxHp() - player.getHp();
        int score = STARTING_POINTS - secondsElapsed - hpLost * 50;
        return Math.max(score, 0);
    }

    private void handleGuess(String input) {
        if (input.length() == 1) {
            handleLetterGuess(input);
        } else {
            handleWordGuess(input);
        }
    }

    private void handleWordGuess(String input) {
        if (capital.toLowerCase().equals(input)) {
            guessedLetters.addAll(notGuessedLetters);
            notGuessedLetters.clear();
            cw.printMessage("rightWord");
        } else {
            player.decreaseHp(WRONG_WORD_DAMAGE);
            cw.printMessage("wrongWord");
        }
    }

    private void handleLetterGuess(String input) {
        if (notGuessedLetters.contains(input)) {
            guessedLetters.add(input);
            notGuessedLetters.remove(input);
            cw.printMessage("rightLetter");
        } else {
            notInWord.add(input);
            player.decreaseHp(WRONG_LETTER_DAMAGE);
            cw.printMessage("wrongLetter");
        }
    }

    private void initializeNotGuessedLetters() {
        for (String letter : capital.split("")) {
            if (!letter.equals(" ")) {
                notGuessedLetters.add(letter.toLowerCase());
            }
        }
    }

    private boolean isGameWon() {
        return notGuessedLetters.isEmpty();
    }

    private boolean isGameLost() {
        return player.getHp() <= 0;
    }

    private boolean isGameOver() {
        return isGameWon() || isGameLost();
    }

    Set<String> getNotInWord() {
        return notInWord;
    }

    String getCountry() {
        return country;
    }

    String getCapital() {
        return capital;
    }

    Set<String> getGuessedLetters() {
        return guessedLetters;
    }
}
