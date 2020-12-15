package ru.otus.task03.builder;

import ru.otus.task03.model.Question;

public class QuestionBuilder implements Builder<Question> {
    private int id;
    private String question;

    public QuestionBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public QuestionBuilder setQuestion(String question) {
        this.question = question;
        return this;
    }

    @Override
    public Question build() {
        return new Question(id, question);
    }
}
