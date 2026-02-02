import xjtlu.cpt111.assignment.quiz.model.Question;

import java.util.List;

public class Quiz {
    private List<Question> questions;
    private int score;

    //Parameter constructor
    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.score = 0;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
