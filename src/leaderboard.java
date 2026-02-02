import java.util.*;

public class leaderboard {
    private Map<String, Integer> scores;//string stores the topics int stores the score

    public leaderboard() {
        this.scores = new HashMap<>();
    }
    public void updateScore(String topic, int score) {
        if (scores.containsKey(topic)) {//Update immediately if it exists
            scores.put(topic, scores.get(topic) + score);
        } else {
            scores.put(topic, score);
        }
    }

    public void displayLeaderboard() {
        //read resources/scores.csv this file
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

        //Generate leaderboards based on selected topics
        Map<String, Integer> topicScores = new HashMap<>();//new map
        for (Scores score : scoresList) {
            if (score.getTopic().equals(topic)) {//same themes
                String name = score.getName();
                int userScore = score.getScore();
                topicScores.put(name, userScore);;
            }
        }

        //Order the score from highest to lowest
        List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>(topicScores.entrySet());
        sortedScores.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        //display it
        System.out.println("Leaderboard for " + topic + ":");
        for (Map.Entry<String, Integer> entry : sortedScores) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}