import java.util.Date;
import java.util.Scanner;
import java.lang.Object;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;

/*
Write a hangman game that randomly generates a word and prompts the user to guess one
letter at a time, as shown in the sample run. Each letter in the word is displayed as an asterisk.
When the user makes a correct guess, the actual letter is then displayed. When the user
finishes a word, display the number of misses and ask the user whether to continue to play with
another word. The program reads the words stored in a text file named hangman.txt. Words
are delimited by spaces. Also make sure you properly handle exceptions in the program like
*/

class Hangman {
 public static void main(String[] args) throws IOException {

 	//read words hangman.txt
 	//store words in an array
 		ArrayList<String> wordList = new ArrayList<String>();
		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader("hangman.txt");
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				wordList.add(sCurrentLine);
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	String word = randomWordGenerator(wordList);
	ArrayList<String> used = new ArrayList<String>();
 	//selects word from random word generator
 	String asterisk = new String(new char[word.length()]).replace("\0", "*");

 	Scanner x = new Scanner(System.in);

 	int count = 0;
 	boolean end = false;
 	while(end == false){

 		System.out.println("(Guess) Enter a letter in word " + asterisk + " >");
 		String guess = x.next();
 		boolean repeat = false;

		for (int i = 0; i < used.size(); i++) {
			if(guess.equals(used.get(i))){
				repeat = true;
			}
		}

 		//check if guess is in the word
 		String newasterisk = "";
		for (int i = 0; i < word.length(); i++) {
			//System.out.println(asterisk.charAt(i));

			if (word.charAt(i) == guess.charAt(0)) {
				newasterisk += guess.charAt(0);
				used.add(guess);
			} 
			else if (asterisk.charAt(i) != '*') {
				newasterisk += word.charAt(i);
			} 
			else {
				newasterisk += "*";
			}
		}

			if (asterisk.equals(newasterisk)){
				if(repeat == true)
					System.out.println("      " + guess + " is already in the word");
				else{
					count++;
					System.out.println("      " + guess + " is not in the word");
				}
			}
			else {
				asterisk = newasterisk;
			}

		if (asterisk.equals(word)) {
			System.out.println("You guessed right! The word is " + word);
			System.out.println("(Not including repeats) You made " + count + " incorrect guesses");
			System.out.println("Wanna play again? (1 for yes, 2 for no) ");
			int num = x.nextInt();
			if(num == 1){
				word = randomWordGenerator(wordList);
				asterisk = new String(new char[word.length()]).replace("\0", "*");
				used.clear();
			}
			else
				end = true;
		}
 	}

 	x.close();

  }


//I want this to work for ArrayList
 public static String randomWordGenerator(ArrayList<String> words){
 	int rdm = new Random().nextInt(words.size());
 	String temp = words.get(rdm);
 	return temp;
  }

}

