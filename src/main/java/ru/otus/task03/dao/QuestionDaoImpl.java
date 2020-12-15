package ru.otus.task03.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.task03.config.AppProps;
import ru.otus.task03.model.Question;
import ru.otus.task03.parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Service
public class QuestionDaoImpl implements QuestionDao{

    private final Parser<Question> parser;
    private final String filename;

    public QuestionDaoImpl(@Qualifier("questionParserImpl") Parser<Question> parser, AppProps props, MessageSource messageSource) {
        this.parser = parser;
        this.filename = messageSource.getMessage("questionSource", null, props.getLocale());
    }

    @Override
    public Map<Integer, Question> getMapOfQuestions() {
        List<Question> questions = new ArrayList<>();
        parser.parseCsv(filename, questions::add);
        return questions.stream()
                .collect(toMap(k -> k.getId(), v -> v));
    }
}
