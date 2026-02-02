import java.util.ArrayList;
import java.util.List;

public class Users {
    private String id;
    private String name;
    private String password;
    private List<Quiz> quizzes;

    //Parameter constructor
    public Users(String id, String name, String password) {
         this.id = id;
         this.name = name;
         this.password = password;
         this.quizzes = new ArrayList<>();
    }
    //getter and setter functions
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
        if (quizzes.size() > 3) {
            quizzes.remove(0); // The system only keeps the results for three times
        }
    }
    public List<Quiz> getQuizzes() {
        return quizzes;
    }
}
