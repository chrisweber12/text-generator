/**
 * This file drives the usage of two different implementations of simulating text
 * generation based on training text -
 * one using an ArrayList and one using a Map
 *
 */
import java.util.Scanner;


public class driver {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter path of training text: ");
        String book = keyboard.next().trim();
        System.out.print("Enter ngram length: ");
        int ngramLength = keyboard.nextInt();
        System.out.print("Enter length of output (in characters): ");
        int numLetters = keyboard.nextInt();

        ProbableTextWithArrayList list = new ProbableTextWithArrayList(book, ngramLength);
        ProbableTextWithMap map = new ProbableTextWithMap(book, ngramLength);

        System.out.println("\nArrayList implementation:");
        list.printRandom(numLetters);
        System.out.println("\nMap implementation:");
        list.printRandom(numLetters);
        System.out.println();

        keyboard.close();
    }
}
