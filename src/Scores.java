public class Scores {
    private String Name;
    private String topic;
    private int score;
    //value constructor
    public Scores(String name, String topic, int score) {
        this.Name = name;
        this.topic = topic;
        this.score = score;
    }
    //getter and setter functions
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

