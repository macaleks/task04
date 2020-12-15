package ru.otus.task03.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.task03.config.AppProps;
import ru.otus.task03.model.Answer;
import ru.otus.task03.parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
public class AnswerDaoImpl implements AnswerDao {

    private final Parser<Answer> parser;
    private final String filename;

    public AnswerDaoImpl(@Qualifier("answerParserImpl") Parser<Answer> parser, AppProps props, MessageSource messageSource) {
        this.parser = parser;
        this.filename = messageSource.getMessage("answerSource", null, props.getLocale());
    }

    @Override
    public Map<Integer, List<Answer>> getMapOfAnswers() {
        List<Answer> answers = new ArrayList<>();
        parser.parseCsv(filename, answers::add);
        return answers.stream()
                .collect(groupingBy(Answer::getId));
    }
}
