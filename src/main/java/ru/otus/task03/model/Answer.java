package ru.otus.task03.model;

import java.util.Objects;

public class Answer {
    private final int id;
    private final String answer;
    private final boolean correct;

    public Answer(int id, String answer, boolean correct) {
        this.id = id;
        this.answer = answer;
        this.correct = correct;
    }

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answer='" + answer + '\'' +
                ", correct=" + correct +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer1 = (Answer) o;
        return id == answer1.id &&
                correct == answer1.correct &&
                Objects.equals(answer, answer1.answer);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, answer, correct);
    }
}
