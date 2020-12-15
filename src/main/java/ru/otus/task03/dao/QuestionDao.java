package ru.otus.task03.dao;

import ru.otus.task03.model.Question;

import java.util.Map;

public interface QuestionDao {

    public Map<Integer, Question> getMapOfQuestions();
}
