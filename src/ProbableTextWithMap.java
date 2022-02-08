/**
 * This class analyzes training text and implements an custom ArrayOfListsMap
 * to generate probable text based on that training text. The probable text is generated
 * based on the characters previously generated and the most likely characters
 * to come next. This string of previous characters is called the nGram, and the
 * number of characters considered is denoted as nGramLength.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class ProbableTextWithMap {

	// Instance variables
	private ArrayOfListsMap<String, ArrayList<Character>> map;
	private String bookText;
	private int nGramLength;

	// Constructor
	public ProbableTextWithMap(String fileName, int ngramLength) {
		getBookText(fileName);
		nGramLength = ngramLength;
		createMap();
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

	// Create ArrayOfListsMap with nGram keys and ArrayList of following char values
	private void createMap() {
		map = new ArrayOfListsMap<String, ArrayList<Character>>();
		// Loop through each nGram in training text
		for (int i = 0; i < bookText.length() - nGramLength; i++) {
			String nGram = bookText.substring(i, i + nGramLength);
			if (!map.containsKey(nGram)) { // Add new nGram entry to map
				ArrayList<Character> followingCharList = new ArrayList<Character>();
				map.put(nGram, followingCharList);
			}
			// Add char to ArrayList in nGram map entry
			ArrayList<Character> newList = map.get(nGram);
			newList.add(bookText.charAt(i + nGramLength));
			map.put(nGram, newList);
		}
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
			// Get char chosen randomly from list in map (likely chars exist more in the list)
			rand = new Random();
			char newChar = map.get(nGram).get(rand.nextInt(map.get(nGram).size()));
			probableText += newChar;
			// Shift nGram by 1 letter and repeat
			nGram = nGram.substring(1) + newChar;
		}
		return probableText;
	}
}
