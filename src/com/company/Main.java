package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.util.stream.Collectors.toCollection;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String originalTitle = randomMovie();
        String hiddenTitle = codedMovie(originalTitle);

//        System.out.println("cheat: " + originalTitle);

        Scanner guessLetter = new Scanner(System.in);

        int wrong = 0;
        ArrayList<Character> originalTitleList = originalTitle.chars().mapToObj(x -> (char) x).collect(toCollection(ArrayList::new));
        ArrayList<Character> hiddenTitleList = hiddenTitle.chars().mapToObj(x -> (char) x).collect(toCollection(ArrayList::new));
        ArrayList<Character> wrongLetters = new ArrayList<>();

        while (true) {
            String changing = "";
            for (Character c : hiddenTitleList)
            {
                changing += c;
            }
            if (changing.equals(originalTitle)) {
                System.out.println("You Won!!!");
                break;
            }
            System.out.println("You're guessing: " + changing);
            System.out.print("You have guessed (" + wrong + ") wrong letters: ");
            for (Character c :
                    wrongLetters) {
                System.out.print(c + " ");
            }
            System.out.println();
            System.out.println("Guess a letter: ");
            char input = guessLetter.next().charAt(0);

            if (originalTitleList.contains(input)) {
                boolean ifWin = false;
                int location = originalTitleList.indexOf(input);
                hiddenTitleList.set(location, input);
                originalTitleList.set(location, '1');
            } else {
                wrong++;
                wrongLetters.add(input);
                if (wrong > 5) {
                    System.out.println("End of the game :(");
                    break;
                }
            }
        }
    }

    public static String codedMovie(String movie) {
        String coded = movie.replaceAll("[a-z]", "_");

        return coded;
    }

    public static String randomMovie() throws FileNotFoundException {
        File listOfMovies = new File("movies.txt");
        Scanner scanner = new Scanner(listOfMovies);

        ArrayList<String> allMovies = new ArrayList<>();
        int movieCount = 0;
        while (scanner.hasNextLine()) {
            String current = scanner.nextLine();
            allMovies.add(current);
            movieCount++;
        }

        int toPlay = (int) (Math.random() * movieCount);

        return allMovies.get(toPlay);
    }
}
