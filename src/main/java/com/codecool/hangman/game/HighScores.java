package com.codecool.hangman.game;

import com.codecool.hangman.file.FileHandler;

import java.io.*;

public class HighScores implements Serializable {
    final FileHandler fh;
    final byte SIZE = 10;
    final byte LAST_ELEM_ID = 9;
    String[] names;
    int[] scores;

    public HighScores(FileHandler fh) throws IOException {
        this.fh = fh;
        names = new String[SIZE];
        scores = new int[SIZE];
        load();
    }

    public void load() throws IOException {
        byte i = 0;
        for (String[] line : fh.getKeysAndValues()) {
            scores[i] = Integer.parseInt(line[0]);
            names[i++] = line[1];
        }
    }

    public void save() throws FileNotFoundException {
        fh.saveToFile(toString().trim());
    }

    /**
     * @param name  name of player
     * @param score score achieved by player
     * @return true if score added to high scores, false if not
     */
    public boolean addToHighScores(String name, int score) {
        byte position = findPositionForInsertion(score);

        if (position == 10) {
            return false;
        }

        for (byte i = LAST_ELEM_ID; i > position; i--) {
            scores[i] = scores[i - 1];
            names[i] = names[i - 1];
        }
        scores[position] = score;
        names[position] = name;
        return true;
    }

    private byte findPositionForInsertion(int score) {
        byte start = 0;
        byte end = SIZE;
        while (start < end) {
            byte mid = (byte) ((start + end) >>> 1);
            if (scores[mid] < score) {
                end = mid;
            } else {
                start = (byte) (mid + 1);
            }
        }
        return start;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            sb.append(scores[i]).append(" | ").append(names[i]).append("\n");
        }
        return sb.toString();
    }
}
