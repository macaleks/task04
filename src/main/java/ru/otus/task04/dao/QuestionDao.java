package ru.otus.task04.dao;

import ru.otus.task04.model.Question;

import java.util.Map;

public interface QuestionDao {

    public Map<Integer, Question> getMapOfQuestions();
}
