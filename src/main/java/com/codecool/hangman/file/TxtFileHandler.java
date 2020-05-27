package com.codecool.hangman.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TxtFileHandler implements FileHandler {
    final String filePath;

    public TxtFileHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String[] getRandomLineFromFile() throws IOException {

        String countryAndCapital = getLine(
                FileUtils.lineIterator(
                        new File(filePath)));
        return countryAndCapital.split(" \\| ", 2);
    }

    private String getLine(Iterator<String> pool) {
        String line = null;
        long count = 0;
        while (pool.hasNext()) {
            String current = pool.next();
            count++;
            if (count <= 1) {
                line = current;
            } else if (ThreadLocalRandom.current().nextLong(count) < 1) {
                line = current;
            }
        }
        return line;
    }

    @Override
    public String[][] getKeysAndValues() throws IOException {
        List<String> content = Files.readAllLines(Paths.get(filePath));
        String[][] lines = new String[content.size()][2];

        int i = 0;
        for (String line : content) {
            lines[i++] = line.split(" \\| ");
        }
        return lines;
    }

    @Override
    public void saveToFile(String text) throws FileNotFoundException {
        try (PrintStream ps = new PrintStream(filePath)) {
            ps.println(text);
        }
    }
}
