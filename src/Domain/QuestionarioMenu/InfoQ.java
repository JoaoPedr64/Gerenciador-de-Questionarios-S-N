package Domain.QuestionarioMenu;

import Domain.Clear;

import java.util.Scanner;

public class InfoQ {
    Clear clear = new Clear();
    Scanner scanner = new Scanner(System.in);
    private String question;
    private int id;

    public InfoQ(String question, int id, String answer) {
        this.question = question;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
