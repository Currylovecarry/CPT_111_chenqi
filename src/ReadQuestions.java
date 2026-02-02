import xjtlu.cpt111.assignment.quiz.model.Question;
import xjtlu.cpt111.assignment.quiz.util.IOUtilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadQuestions {

	private static final String RESOURCES_PATH = "src/main/resources/";
	private static final String QUESTIONS_BANK_PATH = "resources/questionsBank/";
	private static final String EMPTY_FOLDER = "resources/questionsBank/emptyFolder";


	//Method 1: Get the problem from the xml file
	public static Question[] readQuestionsFromXML(String filename) {
		try {
			System.out.println("===\n=== read questions - started\n===");
			Question[] questions = IOUtilities.readQuestions(filename);
			if (null == questions || questions.length == 0) {
				System.out.println("Questions is empty!");
			} else {
				int numQuestions = 0;
				for (Question question : questions) {
					System.out.println("Question #" + (++numQuestions) + " " + question.toString());
				}
			}
			return questions;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			System.out.println("===\n=== read questions - complete\n===");
		}
	}
	//Method 2: Obtain user information from the file path
	public static List<Users> readUsers(String filePath) {
		System.out.println("===\n=== read users - started\n===");
		List<Users> users = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 3) {
					users.add(new Users(parts[0], parts[1], parts[2]));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("===\n=== read users - complete\n===");
		}
		return users;
	}
	//Method 3: Write user information to a file
	public static void writeUsers(String filePath, List<Users> users) {
		System.out.println("===\n=== write users - started\n===");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			for (Users user : users) {
				writer.write(user.getId() + "," + user.getName() + "," + user.getPassword());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("===\n=== write users - complete\n===");
		}
	}
	//Method 4 :Read scores from a file
	public static List<Scores> readScores(String filePath) {
		System.out.println("===\n=== read scores.csv - started\n===");
		List<Scores> scores = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 3) {
					scores.add(new Scores(parts[0], parts[1], Integer.parseInt(parts[2])));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("===\n=== read scores.csv - complete\n===");
		}
		return scores;
	}
	//Method 5: Write the score to a file
	public static void writeScores(String filePath, List<Scores> scores) {
		System.out.println("===\n=== write scores.csv - started\n===");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			for (Scores score : scores) {
				writer.write(score.getName() + "," + score.getTopic() + "," + score.getScore());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("===\n=== write scores.csv - complete\n===");
		}
	}
	public static void main(String... arguments) {
		String filename = RESOURCES_PATH + "questions.xml";
		filename = QUESTIONS_BANK_PATH + "questions.xml";
		// filename = RESOURCES_PATH;
		filename = QUESTIONS_BANK_PATH;
		// filename = EMPTY_FOLDER;


		try {
			System.out.println("===\n=== read questions - started\n===");
			Question[] questions = IOUtilities.readQuestions(filename);
			if (null == questions || questions.length == 0) {
				System.out.println("Questions is empty!");
			} else {
				int numQuestions = 0;
				for (Question question : questions) {
					System.out.println("Question #" + (++numQuestions) + " " + question.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("===\n=== read questions - complete\n===");
		}

	}

}
