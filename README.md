# Hangman

A simple console based game. Guess capital of the country to win.  
Don't make too many mistakes, or you will lose.

## Description

When you start new game random country is chosen, and you have to guess it's capital.  
You can provide a letter, or whole word - program will recognise which one was provided. For wrong letter 1 health point is divided, for wrong word - 2. You lose, when your hp reaches 0.  
You can change the starting hp, and language in settings.

## Getting Started

### Prerequisites

* Maven 3.6
* Java 13, or 14

### Executing program
For java 13:
```
mvn install && java --enable-preview -jar target/hangman.jar
```
For java 14 you have to edit java version in pom.xml, and then run it like this:
```
mvn install && java -jar target/hangman.jar
```

## Author

Mateusz Gołda  
