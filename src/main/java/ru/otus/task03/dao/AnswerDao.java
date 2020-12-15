package ru.otus.task03.dao;

import ru.otus.task03.model.Answer;

import java.util.List;
import java.util.Map;

public interface AnswerDao {

    public Map<Integer, List<Answer>> getMapOfAnswers();
}
