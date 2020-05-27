package com.codecool.hangman.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader {

    private final BufferedReader br;
    private final ConsoleWriter cw;

    ConsoleReader(ConsoleWriter cw) {
        br = new BufferedReader(new InputStreamReader(System.in));
        this.cw = cw;
    }

    void waitForInput() throws IOException {
        br.readLine();
    }

    String getLine() throws IOException {
        String input = null;
        boolean validInput = false;
        while (!validInput) {
            input = br.readLine();
            if (input.isEmpty()) {
                cw.printMessage("emptyInput");
            } else {
                validInput = true;
            }
        }
        return input;
    }

    int getIntInput() throws IOException {
        int input = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                input = Integer.parseInt(br.readLine());
                validInput = true;
            } catch (NumberFormatException e) {
                cw.printMessage("notNumber");
            }
        }
        return input;
    }

    int getIntInputInRange(int min, int max) throws IOException {
        int input = 0;
        boolean validInput = false;
        while (!validInput) {
            input = getIntInput();
            if (min <= input && input <= max) {
                validInput = true;
            } else {
                cw.print(String.format(
                        cw.getMessage("numberOutOfRange"), min, max));
            }
        }
        return input;
    }
}
