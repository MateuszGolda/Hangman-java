package com.codecool.hangman.file;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileHandler {
    String[] getRandomLineFromFile() throws IOException;

    String[][] getKeysAndValues() throws IOException;

    void saveToFile(String text) throws FileNotFoundException;
}
