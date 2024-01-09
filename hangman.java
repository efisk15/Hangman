// Lab: Hangman Program.
// Eli Fisk.
// This program allows a user to enter a random integer that the program will use to choose
// a random word that the user can then play hangman with. The program will ask the user
// to enter their guesses for the word and the program will print the hangman picture after
// each guess followed by the target word filled in with only the correct letters the user
// has guessed so far. 
// Ben Fisk.

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

class hangman {

// Error check user input at every point in the program making sure they first enter a number then
// only enter letters. Call and control methods to run the hangman game.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean bool = true;
        int seed;
        while(true) {
            System.out.print("Enter seed: ");
            if (sc.hasNextInt()) {
                seed = sc.nextInt();
                break;
            }
            else {
                sc.next();
            }
        }
        hangman hang = new hangman(seed);
        do {
        hang.print_hangman();
        hang.print_letters();
        if (hang.won()) {
            break;
        }
        do{
            bool = false;
            System.out.print("Guess a letter: ");
            String word = sc.next();
            if(word.length() <= 1) {
                char letter = word.toLowerCase().charAt(0);
                if (letter >= 97 && letter <= 122) {
                    if (hang.guess(letter)) {
                        bool = true;
                        if(!wordlist[hang.chosen_word].contains(word.toLowerCase())) {
                            ++ hang.wrong_guesses;
                        }
                    }
                    else {
                        System.out.println("You already guessed that letter!");
                    }
                }
                else {
                    continue;
                }
            }
            else {
                continue;
            }
        }while(!bool);
        if (hang.lost() || hang.won()) {
            bool = false;
        }
        }while(bool);
        if(hang.lost()) {
        hang.print_hangman();
        System.out.println("Better luck next time!");
        }
        if(hang.won()) {
            System.out.println("YOU GOT IT!");
        }
        sc.close();
    }

// Generate word from given seed.
    public hangman(long seed) {
        Random rng = new Random(seed);
        chosen_word = rng.nextInt(wordlist.length);
    }

// Check if user has won.
    public boolean won() {
        if(gotIt) {
            return true;
        }
        else {
            return false;
        }
    }
    
// Check if user has lost.
    public boolean lost() {
        return wrong_guesses >= pics.length-1;
    }

// Print underscores for each letter in the chosen word. As the game goes on, switch underscores
// with letters the user has correctly chosen.
    public void print_letters() {
        StringBuilder sb = new StringBuilder();
        String string = "";
        int check = 0;
        for (int i = 0;i < wordlist[chosen_word].length();i+=1) {
            char letter = wordlist[chosen_word].charAt(i);
            if(guesses.contains(letter)) {
                sb.append(letter);
                sb.append(" ");
                ++check;
            }
            else {
                sb.append("_ ");
            }
            string = sb.toString();
        }
        if (check == wordlist[chosen_word].length()) {
            gotIt = true;
        }
        System.out.println(string);
    }

// Check to see if the letter the user guesses is is in the given word.
    public boolean guess(char letter) {
        if(guesses.contains(letter)) {
            return false;
        }
        else {
        guesses.add(letter);
        return true;
        }
    }

// Print the hangman picture associated with the number of wrong guesses.
    public void print_hangman() {
        System.out.println(pics[wrong_guesses]);
    }

    private boolean gotIt;
    private int wrong_guesses;
    private int chosen_word;
    private ArrayList<Character> guesses = new ArrayList<>();

    final static String[] wordlist = {
        "elephant", "giraffe", "tiger", "lion", "monkey", "bear", "whale",
        "dolphin", "fish", "bird", "apple", "banana", "orange", "pear",
        "watermelon", "pizza", "cake", "cream", "cookie", "sandwich",
        "city", "country", "mountain", "ocean", "river", "lake", "forest",
        "desert", "beach", "park", "car", "house", "boat", "train", "plane",
        "book", "cellular", "laptop", "television", "clock", "man", "woman",
        "child", "boy", "girl", "doctor", "nurse", "teacher", "firefighter",
        "officer", "play", "read", "write", "sing", "dance", "swim", "sprint",
        "jump", "sports", "watch", "table", "chair", "bed", "couch", "lamp",
        "door", "window", "wall", "floor", "ceiling", "phone", "computer",
        "stereo", "alarm", "journal", "magazine", "newspaper", "pencil", "crayon", "paper",
        "cab", "semi", "railway", "hoverboard", "helicopter", "bike", "jetski", "skateboard",
        "rollerskates", "scooter", "puppy", "kitten", "horse", "bovine", "swine", "sheep",
        "rooster", "duck", "goose", "rabbit", "fusion", "moon", "star", "cloud",
        "rain", "snow", "wind", "thunder", "lightning", "rainbow", "happy",
        "exhausted", "angry", "excited", "scared", "surprised", "tired",
        "hungry", "thirsty", "bored"
    };

    static final String[] pics = {
        "  +---+\n" +
        "  |   |\n" +
        "      |\n" +
        "      |\n" +
        "      |\n" +
        "      |\n" +
        "=========",
    
        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        "      |\n" +
        "      |\n" +
        "      |\n" +
        "=========",
    
        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        "  |   |\n" +
        "      |\n" +
        "      |\n" +
        "=========",

        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        " /|   |\n" +
        "      |\n" +
        "      |\n" +
        "=========",

        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        " /|\\  |\n" +
        "      |\n" +
        "      |\n" +
        "=========",
        
        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        " /|\\  |\n" +
        " /    |\n" +
        "      |\n" +
        "=========",

        "  +---+\n" +
        "  |   |\n" +
        "  O   |\n" +
        " /|\\  |\n" +
        " / \\  |\n" +
        "      |\n" +
        "=========",
    };
}