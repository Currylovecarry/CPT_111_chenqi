import xjtlu.cpt111.assignment.quiz.model.Option;
import xjtlu.cpt111.assignment.quiz.model.Question;

import java.util.*;

public class QuizTools {
    //User set
    private List<Users> users;
    //Questions set
    private List<Question> questions;
    //Quiz set
    private List<Quiz> quiz;
    //Define an object for learderborad
    private leaderboard leaderboard;

    public QuizTools() {
        this.users = new ArrayList<>();
        this.questions = new ArrayList<>();
        this.quiz = new ArrayList<>();
        this.leaderboard = new leaderboard();
    }
    public void readdata() {
        users = ReadQuestions.readUsers("resources/user.csv"); // Used to read user information from csv
    }
    public Users login() {
        Scanner userslogin = new Scanner(System.in);
        System.out.println("Please enter your id: ");
        String username = userslogin.nextLine();
        System.out.println("Please enter your password: ");
        String password = userslogin.nextLine();

        for (Users user : users) {//Traverse user data
            if(user.getId().equals(username) && user.getPassword().equals(password)) {
                System.out.println("You have successfully logged in!");
                return user;
            }
        }
        System.out.println("You have entered an incorrect username or password!");
        return null;
    }
    public void register() {
        loop:while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Please enter your name: ");
            String name = input.nextLine();
            System.out.println("Please enter your id: ");
            String id = input.nextLine();
            System.out.println("Please enter your password: ");
            String password = input.nextLine();

            if (isIDexist(id)==false) {
                users.add(new Users(id,name,password));//Add a new user
                ReadQuestions.writeUsers("resources/user.csv", users);//Write user information to a csv file
                System.out.println("User registered successfully!");//A registration success message is displayed
                break loop;
            }
            else {
                System.out.println("User already exists!");
            }
        }
    }
    public static boolean isIDexist(String id) {
        //Traverse user data
        List<Users> users;
        users = ReadQuestions.readUsers("resources/user.csv");
        for (Users user : users) {
            if(user.getId().equals(id)) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
    //User selection menu
    public void userMenu(Users user) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Take Quiz");
            System.out.println("2. View Dashboard");
            System.out.println("3. View Leaderboard");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); //make choice

            switch (choice) {
                case 1:
                    takeQuiz(user);
                    break;
                case 2:
                    new historyboard(user).displayDashboard();
                    break;
                case 3:
                    leaderboard.displayLeaderboard();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    //Used to randomly select questions from the question bank
    private List<Question> selectQuestionsForQuiz() {
        List<Question> selectedQuestions = new ArrayList<>();
        if (questions.isEmpty()) {
            return selectedQuestions;
        }
    //Use random numbers to randomly select the question bank
        Random random = new Random();
        while (selectedQuestions.size() < Math.min(5, questions.size())) {//The number of question is 5
            Question question = questions.get(random.nextInt(questions.size()));
            if (!selectedQuestions.contains(question)) {
                selectedQuestions.add(question);
            }
        }
        return selectedQuestions;
    }
    public static void randomTheOption(Option[] array) {
        Random randomOption = new Random();
        for (int i = 0; i < array.length; i++) {
            int index = randomOption.nextInt(array.length);
            Option temp = array[index];//Use temporary numbers to scramble
            array[index] = array[i];
            array[i] = temp;
        }
    }
    public static void RamdomTheOption(Option[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            int index = rand.nextInt(array.length);
            Option temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
    public void takeQuiz(Users user) {
        Scanner scannerQuiztype = new Scanner(System.in);

        //Let the user select the question type
        System.out.println("Please select the question type:");
        System.out.println("1. Computer Science");
        System.out.println("2. Mathematics");
        System.out.println("3. EE");
        System.out.println("4. English");
        System.out.print("Enter the number of your choice: ");
        int choice = scannerQuiztype.nextInt();
        questions.clear();//clear previous choice of the user and change to new one

        String topic = "";
        //Use the readquestionfromxml method to retrieve the question bank
        switch (choice) {
            case 1:
                topic = "Computer Science";
                Question[] loadedQuestionscs = ReadQuestions.readQuestionsFromXML("resources/questionsBank/questions_cs.xml");
                if (loadedQuestionscs != null) {
                    questions.addAll(Arrays.asList(loadedQuestionscs));
                }
                break;
            case 2:
                topic = "Mathematics";
                Question[] loadedQuestionsmath = ReadQuestions.readQuestionsFromXML("resources/questionsBank/questions_mathematics.xml");
                if (loadedQuestionsmath != null) {
                    questions.addAll(Arrays.asList(loadedQuestionsmath));
                }
                break;
            case 3:
                topic = "EE";
                Question[] loadedQuestionsee = ReadQuestions.readQuestionsFromXML("resources/questionsBank/questions_ee.xml");
                if (loadedQuestionsee != null) {
                    questions.addAll(Arrays.asList(loadedQuestionsee));
                }
                break;
            case 4:
                topic = "English";
                Question[] loadedQuestionsenglish = ReadQuestions.readQuestionsFromXML("resources/questionsBank/questions_english.xml");
                if (loadedQuestionsenglish != null) {
                    questions.addAll(Arrays.asList(loadedQuestionsenglish));
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }
        //Ensure successful retrieval
        List<Question> selectedQuestions = selectQuestionsForQuiz();
        if (selectedQuestions.isEmpty()) {
            System.out.println("No questions available. Please try again later.");
            return;
        }
        //initialize scores
        Quiz quiz = new Quiz(selectedQuestions);
        int score = 0;
        Scanner scanner = new Scanner(System.in);
        //Determine the correctness of the problem
        for (Question question : selectedQuestions) {
            System.out.println(question.getQuestionStatement());
            // Convert Option[] to List<Option>
            Option[] options = question.getOptions();
            //random the options
            RamdomTheOption(options);
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i].getAnswer());
            }
            // Prompts the user to enter and automatically reads the data
            System.out.print("Your answer (enter the number): ");
            int userAnswer = scanner.nextInt();
            scanner.nextLine();
            // Record score
            if (userAnswer > 0 && userAnswer <= options.length && options[userAnswer - 1].isCorrectAnswer()) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
            }
        }

        quiz.setScore(score);
        user.addQuiz(quiz);
        leaderboard.updateScore(user.getName(), score);

        System.out.println("Quiz completed! Your score: " + score + "!");
        //Writes the user-selected theme and score to the file
        Scores newScore = new Scores(user.getName(), topic, score);
        List<Scores> scoresList = ReadQuestions.readScores("resources/scores.csv");
        scoresList.add(newScore); //Add new score
        ReadQuestions.writeScores("resources/scores.csv", scoresList);
    }
}
