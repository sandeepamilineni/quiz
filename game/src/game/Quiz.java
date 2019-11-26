package game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Quiz {

	List<String[]> questions = new ArrayList<>();
	String choice;
	int currentQuestionIndex;

	public void readQuestions() {
		String fileName = "Questions.csv";
		Stream<String> stream;
		try {
			stream = Files.lines(Paths.get(fileName));
			questions = stream.filter(line -> !line.startsWith("Question")).map(line -> line.split(","))
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void displayRandomQuestion() {
		Random rand = new Random();
		currentQuestionIndex = rand.nextInt(questions.size());
		String[] questionLine = questions.get(currentQuestionIndex);

		System.out.println(questionLine[0]);
		
		for (int i = 1; i < questionLine.length - 1; i++) {
			System.out.println(i+" : "+questionLine[i]);
		}
		
	}

	public void validateChoice() {
		String csvLine[] = questions.get(currentQuestionIndex);
		String answer = csvLine[csvLine.length-1];
		String chosenAnswer = csvLine[Integer.parseInt(choice)];
		System.out.println(" chosenAnswer "+ chosenAnswer);
		System.out.println(" answer "+ answer);
		
		  if (!answer.equals(chosenAnswer)) { 
			  System.out.println("Incorrect Answer ");
		  
		  System.exit(1); }
		 

	}

	public static void main(String[] args) {
		Quiz quiz = new Quiz();
		quiz.readQuestions();
		
		while( !quiz.questions.isEmpty()) {
		
		quiz.displayRandomQuestion();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter index of selected choice : ");
		quiz.choice = scanner.next();
		quiz.validateChoice();
		quiz.questions.remove(quiz.currentQuestionIndex);
		}
	}

}
