import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class historyboard {
    private Users user;

    public historyboard(Users user) {
        this.user = user;
    }

    public void displayDashboard() {
        System.out.println("User Dashboard for " + user.getName());
        //Read the data stored in the file
        List<Scores> scoresList = ReadQuestions.readScores("resources/scores.csv");

        //Prompts the user to enter a theme
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select the topic:");
        System.out.println("1. Computer Science");
        System.out.println("2. Mathematics");
        System.out.println("3. EE");
        System.out.println("4. English");
        System.out.print("Enter the number of your choice: ");
        int choice = scanner.nextInt();

        String topic = "";
        switch (choice) {
            case 1:
                topic = "Computer Science";
                break;
            case 2:
                topic = "Mathematics";
                break;
            case 3:
                topic = "EE";
                break;
            case 4:
                topic = "English";
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }

        List<Scores> userScores = new ArrayList<>();
        //Look for only one subject
        for (Scores score : scoresList) {
            if (score.getName().equals(user.getName()) && score.getTopic().equals(topic)) {//Same as user theme
                userScores.add(score);
            }
        }

        //Displays the latest three historical records of the current login user in the specified topic
        int attemptCount = 1;
        int displayCount = 0;
        for (int i = userScores.size() - 1; i >= 0; i--) {
            Scores score = userScores.get(i);
            System.out.println("Attempt " + attemptCount + ": Quiz Score: " + score.getScore());
            attemptCount++;
            displayCount++;
            //3 tests
            if (displayCount >= 3) {
                break;
            }
        }
    }
}