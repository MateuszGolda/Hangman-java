package com.codecool.hangman.ui;

import java.io.IOException;

/**
 * ConsoleWriter
 */
public class ConsoleWriter {

    private PropertiesReader pr;
    private final ConsoleReader cr;

    public ConsoleWriter() {
        cr = new ConsoleReader(this);
    }

    public void printMessage(String... message) {
        for (String string : message) {
            System.out.println(pr.getProperty(string));
        }
    }

    public void print(String... message) {
        for (String string : message) {
            System.out.println(string);
        }
    }

    public String getMessage(String message) {
        return pr.getProperty(message);
    }

    public void waitForInput(String... message) throws IOException {
        printMessage(message);
        cr.waitForInput();
    }

    public String getInput(String... message) throws IOException {
        printMessage(message);
        return cr.getLine();
    }

    public int getIntInput(String... message) throws IOException {
        printMessage(message);
        return cr.getIntInput();
    }

    public int getIntInputInRange(int min, int max, String... message) throws IOException {
        printMessage(message);
        return cr.getIntInputInRange(min, max);
    }

    public void setLanguage(String languagePath) {
        pr.setFileName(pr.getProperty(languagePath));
    }

    public void setPr(PropertiesReader pr) {
        this.pr = pr;
    }

    public void clearScreen() {
        System.out.println("\033[H\033[2J");
    }
}
