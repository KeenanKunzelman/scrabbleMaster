package csc312;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ScrabbleMaster {

    /**
     * @param game number of the game to be played, i.e. game 1, 2 or 3
     * @param column column number from 1 to 5
     * @param row row number from 1 to 5
     * @return a url to get a character from
     */
    private static String setURLGrid(int game, int column, int row) {
        HashMap<Integer, Character> charColumn = new HashMap<>();
        charColumn.put(1, 'a');
        charColumn.put(2, 'b');
        charColumn.put(3, 'c');
        charColumn.put(4, 'd');
        charColumn.put(5, 'e');

        return "https://wordfinder-001.appspot.com/wordfinder?game=" + game + "&pos=" + charColumn.get(column) + row;
    }

    /**
     * Get character from url
     * @param url url to get a letter from
     * @return a single character (character in the grid)
     */
    static Character getURL(String url) {
        // Get character
        String inputLine;
        URL urlLook;
        int statusCode = 0;

        try {
            // create new URL object
            urlLook = new URL(url);
            // Open new HTTP connection
            HttpURLConnection connection = (HttpURLConnection) urlLook.openConnection();
            // Get the response code from connection
            statusCode = connection.getResponseCode();
            // Check status code and perform action
            switch(statusCode) {
                // When status code is 200 == OK
                case 200:
                    // Create new BufferedReader object to store content
                    BufferedReader in = new BufferedReader(new InputStreamReader(urlLook.openStream()));
                    // Read content
                    while ((inputLine = in.readLine()) != null) {
                        return inputLine.charAt(0);
                    }
                    // Close buffer reader
                    in.close();
                default:
                    // break when status code is not 200, e.g. 404, 403 or 500
                    break;
            }
        }

        // Handle exceptions
        catch (UnknownHostException u) {
            System.out.print("Unknown host");
        }
        catch (MalformedURLException e) {
            System.out.print("Malformed URL");
        }
        catch (IOException i) {
            System.out.print("IO Exception");
        }
        return null;
    }

    /**
     * Get letters from the grid and form strings
     * @param gameNumber game to be played. i.e. game 1, 2 or 3
     * @return An array list with 10 5-letter words
     */
    static ArrayList<String> setGrid(int gameNumber) {
        ArrayList<String> arrWords = new ArrayList<>();
        StringBuilder wordRow = new StringBuilder();
        String url;
        Character letter;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                url = setURLGrid(gameNumber, i + 1, j + 1);
                letter = getURL(url);
                Cell cell = new Cell(letter, i + 1, j + 1, false);
                wordRow.append(cell.getValue());
            }
            arrWords.add(wordRow.toString());
            wordRow.setLength(0);
        }
        StringBuilder colWord = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                colWord.append(arrWords.get(j).charAt(i));
            }
            arrWords.add(colWord.toString());
            colWord.setLength(0);
        }
        return arrWords;
    }

    /**
     * Retrieve possible words from the server
     * @param url url with all the possible 3 letter words
     * @return An array list with all the 50 words extracted from the url
     */
    private static ArrayList<String> getWords(String url) {
        // Get character
        String inputLine;
        URL urlLook;
        int statusCode = 0;

        try {
            // create new URL object
            urlLook = new URL(url);
            // Open new HTTP connection
            HttpURLConnection connection = (HttpURLConnection) urlLook.openConnection();
            // Get the response code from connection
            statusCode = connection.getResponseCode();
            ArrayList<String> arr = new ArrayList<>();
            // Check status code and perform action
            switch(statusCode) {
                // When status code is 200 == OK
                case 200:
                    // Create new BufferedReader object to store content
                    BufferedReader in = new BufferedReader(new InputStreamReader(urlLook.openStream()));
                    // Read content
                    while ((inputLine = in.readLine()) != null) {
                        arr.add(inputLine);
                    }
                    // Close buffer reader
                    in.close();
                    return arr;
                default:
                    // break when status code is not 200, e.g. 404, 403 or 500
                    break;
            }
        }

        // Handle exceptions
        catch (UnknownHostException u) {
            System.out.print("Unknown host");
        }
        catch (MalformedURLException e) {
            System.out.print("Malformed URL");
        }
        catch (IOException i) {
            System.out.print("IO Exception");
        }
        return null;
    }

    /**
     * Print the words found in the grid
     * @param game game to be played. i.e. game 1, 2 or 3
     */
    private static void findWord(int game) {
        ArrayList<String> wordsToSearchFor = getWords("https://wordfinder-001.appspot.com/word.txt");
        ArrayList<String> wordsToSearchFrom = setGrid(game);

        HashMap<Integer, Character> alphaNumPosition = new HashMap<>();
        alphaNumPosition.put(0, 'A');
        alphaNumPosition.put(1, 'B');
        alphaNumPosition.put(2, 'C');
        alphaNumPosition.put(3, 'D');
        alphaNumPosition.put(4, 'E');

        for (int i = 0; i < wordsToSearchFor.size(); i++) {
            for (int j = 0; j < wordsToSearchFrom.size(); j++) {
                if (wordsToSearchFrom.get(j).contains(wordsToSearchFor.get(i))) {

                    int idx = wordsToSearchFrom.get(j).indexOf(wordsToSearchFor.get(i));
                    /*if (j > 4) {
                        System.out.println("game: " + game + " word: " + wordsToSearchFor.get(i) + " location: " + alphaNumPosition.get(j - 5) + idx + " : " + alphaNumPosition.get(j - 5) + (idx + 2));
                    }
                    else {
                        System.out.println("game: " + game + " word: " + wordsToSearchFor.get(i) + " location: " + alphaNumPosition.get(idx) + j + " : " + alphaNumPosition.get(idx + 2) + j);
                    }*/
                    if (j <= 4) {
                        System.out.println("game: " + game + " word: " + wordsToSearchFor.get(i) + " location: " + alphaNumPosition.get(j) + (idx+ 1) + " : " + alphaNumPosition.get(j) + (idx + 3));
                    }
                    else {
                        System.out.println("game: " + game + " word: " + wordsToSearchFor.get(i) + " location: " + alphaNumPosition.get(idx) + (j - 4) + " : " + alphaNumPosition.get(idx + 2) + (j - 4));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        findWord(1);
        findWord(2);
        findWord(3);
    }
}
