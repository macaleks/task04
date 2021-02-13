package ru.otus.task04.dao;

import ru.otus.task04.model.Answer;

import java.util.List;
import java.util.Map;

public interface AnswerDao {

    public Map<Integer, List<Answer>> getMapOfAnswers();
}
