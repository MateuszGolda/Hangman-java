package com.codecool.hangman.ui;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private String fileName;

    public PropertiesReader(String fileName) {
        this.fileName = fileName;
    }

    public String getProperty(String property) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(fileName));

            return prop.getProperty(property);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
