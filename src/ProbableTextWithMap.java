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

	public ProbableTextWithMap(String fileName, int ngramLength) {
		getBookText(fileName);
		nGramLength = ngramLength;
		createMap();
	}

	public void printRandom(int charsToPrint) {
		String fullText = generateRandom(charsToPrint);
		String output = "";
		int i = 0;
		while (i < 60) {
			if (i >= fullText.length()) {
				System.out.println(output);
				return;
			}
			output += fullText.charAt(i);
			i++;
		}
		while (fullText.charAt(i) != ' ') {
			output += fullText.charAt(i);
			i++;
		}
		output += '\n';
		i++;
		while (i < fullText.length()) {
			if (fullText.charAt(i) == ' ') {
				i++;
			}
			int lineIndex = 0;
			while (lineIndex < 60) {
				if (i >= fullText.length()) {
					System.out.println(output);
					return;
				}
				output += fullText.charAt(i);
				lineIndex++;
				i++;
			}
			while (fullText.charAt(i) != ' ') {
				output += fullText.charAt(i);
				i++;
			}
			output += '\n';
			i++;
		}
		System.out.println(output);
		return;
	}

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

	private void createMap() {
		map = new ArrayOfListsMap<String, ArrayList<Character>>();
		for (int i = 0; i < bookText.length() - nGramLength; i++) {
			String nGram = bookText.substring(i, i + nGramLength);
			if (!map.containsKey(nGram)) {
				ArrayList<Character> followingCharList = new ArrayList<Character>();
				map.put(nGram, followingCharList);
			}
			ArrayList<Character> newList = map.get(nGram);
			newList.add(bookText.charAt(i + nGramLength));
			map.put(nGram, newList);
		}
	}

	private String generateRandom(int maxSize) {
		Random rand = new Random();
		int firstIndex = rand.nextInt(bookText.length() - nGramLength + 1);
		String nGram = "";
		for (int i = 0; i < nGramLength; i++) {
			nGram += bookText.charAt(firstIndex + i);
		}
		String probableText = nGram;
		while (probableText.length() < maxSize) {
			rand = new Random();
			char newChar = map.get(nGram).get(rand.nextInt(map.get(nGram).size()));
			probableText += newChar;
			nGram = nGram.substring(1) + newChar;
		}
		return probableText;
	}
}
