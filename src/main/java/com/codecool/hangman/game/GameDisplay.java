package com.codecool.hangman.game;

import com.codecool.hangman.Settings;
import com.codecool.hangman.ui.ConsoleWriter;

public class GameDisplay {
    private final Player player;
    private final Game game;
    private final ConsoleWriter cw;

    GameDisplay(Player player, Game game, ConsoleWriter cw) {
        this.player = player;
        this.game = game;
        this.cw = cw;
    }

    @Override
    public String toString() {
        return String.format(cw.getMessage("life"), player.getHp())
                + " ".repeat(5)
                + String.format(cw.getMessage("notInWord"), game.getNotInWord().toString())
                + String.format(cw.getMessage("guessTheCapital"), game.getCountry())
                + "\n\n" + displayedWordToString() + "\n"
                + getAsciiArt() + "\n";
    }

    private String displayedWordToString() {
        final String HIDDEN_LETTER = "_ ";

        var sb = new StringBuilder();
        for (String letter : game.getCapital().split("")) {
            if (letter.equals(" ")) {
                sb.append("  ");
            } else {
                sb.append(isLetterGuessed(letter.toLowerCase()) ? letter + " " : HIDDEN_LETTER);
            }
        }
        return sb.toString();
    }

    private boolean isLetterGuessed(String letter) {
        return game.getGuessedLetters().contains(letter);
    }

    private String getAsciiArt() {
        int max = Settings.INSTANCE.getMaxHp();
        int hp = player.getHp();
        return cw.getMessage("hangman" + (5 * hp + max - 1) / max);
    }
}
