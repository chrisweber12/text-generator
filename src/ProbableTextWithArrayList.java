/*
* This class implements an ArrayList to
*
*/
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;


public class ProbableTextWithArrayList {

	private String bookText;
	private int nGramLength;

	public ProbableTextWithArrayList(String bookName, int ngramLength) {
		getBookText(bookName);
		nGramLength = ngramLength;
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

	public void printRandom(int letters) {
		String fullText = generateRandom(letters);
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

	private String generateRandom(int maxSize) {
		Random rand = new Random();
		int firstIndex = rand.nextInt(bookText.length() - nGramLength + 1);
		String nGram = "";
		for (int i = 0; i < nGramLength; i++) {
			nGram += bookText.charAt(firstIndex + i);
		}
		String probableText = nGram;
		while (probableText.length() < maxSize) {
			ArrayList<Character> followingCharList = new ArrayList<Character>();
			for (int i = 0; i < bookText.length() - nGramLength; i++) {
				if (bookText.substring(i, i + nGramLength).equals(nGram)) {
					followingCharList.add(bookText.charAt(i + nGramLength));
				}
			}
			rand = new Random();
			int newCharIndex = rand.nextInt(followingCharList.size());
			char newChar = followingCharList.get(newCharIndex);
			probableText += newChar;
			nGram = nGram.substring(1) + newChar;
		}
		return probableText;
	}

}
