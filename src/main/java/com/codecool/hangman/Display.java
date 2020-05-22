package com.codecool.hangman;

import java.util.Scanner;

/**
 * Display
 */
public class Display {

    public static void startScreen() {
        System.out.println("\n\n\n\n\n\n Press enter to start the game.\n\n\n\n\n\n");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
