/**
 * This class analyzes training text and implements an ArrayList to generate
 * probable text based on that training text. The probable text is generated
 * based on the characters previously generated and the most likely characters
 * to come next. This string of previous characters is called the nGram, and the
 * number of characters considered is denoted as nGramLength.
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;


public class ProbableTextWithArrayList {

	// Instance variables
	private String bookText;
	private int nGramLength;

	// Constructor
	public ProbableTextWithArrayList(String bookName, int ngramLength) {
		getBookText(bookName);
		nGramLength = ngramLength;
	}

	// Read training text into bookText
	private void getBookText(String bookName) {
		bookText = "";
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(bookName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scanner.hasNextLine()) {
			bookText += scanner.nextLine() + " ";
		}
		scanner.close();
	}

	// Print the generated text
	public void printRandom(int letters) {
		// Get generated text
		String fullText = generateRandom(letters);
		String output = "";

		int i = 0;
		// Loop runs once per line written (~60 characters)
		while (i < fullText.length()) {
			if (fullText.charAt(i) == ' ') { // Don't start line with space
				i++;
			}
			int lineIndex = 0;
			// Add characters until you reach 60 (end of line for output)
			while (lineIndex < 60) {
				if (i >= fullText.length()) {
					System.out.println(output);
					return;
				}
				output += fullText.charAt(i);
				lineIndex++;
				i++;
			}
			// Finish word at 60 characters
			while ((i < fullText.length() && (fullText.charAt(i) != ' '))) {
				output += fullText.charAt(i);
				i++;
			}
			output += '\n';
			i++;
		}
		System.out.println(output);
		return;
	}

	// Generate the random text
	private String generateRandom(int maxSize) {
		// Get random string the length of nGram from training text
		Random rand = new Random();
		int firstIndex = rand.nextInt(bookText.length() - nGramLength + 1);
		String nGram = "";
		for (int i = 0; i < nGramLength; i++) {
			nGram += bookText.charAt(firstIndex + i);
		}

		// Add characters to initial nGram to build probableText
		String probableText = nGram;
		while (probableText.length() < maxSize) {
			// Create arrayList of all characters that follow nGram in training text
			ArrayList<Character> followingCharList = new ArrayList<Character>();
			for (int i = 0; i < bookText.length() - nGramLength; i++) {
				if (bookText.substring(i, i + nGramLength).equals(nGram)) {
					followingCharList.add(bookText.charAt(i + nGramLength));
				}
			}
			// Get char chosen randomly from list (likely chars exist more in the list)
			rand = new Random();
			int newCharIndex = rand.nextInt(followingCharList.size());
			char newChar = followingCharList.get(newCharIndex);
			probableText += newChar;
			// Shift nGram by 1 letter and repeat
			nGram = nGram.substring(1) + newChar;
		}
		return probableText;
	}

}
