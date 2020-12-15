package ru.otus.task04.builder;

import ru.otus.task04.model.Answer;

public class AnswerBuilder implements Builder<Answer> {
    private int id;
    private String answer;
    private boolean correct;

    public AnswerBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public AnswerBuilder setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public AnswerBuilder setCorrect(boolean correct) {
        this.correct = correct;
        return this;
    }

    @Override
    public Answer build() {
        return new Answer(id, answer, correct);
    }
}
