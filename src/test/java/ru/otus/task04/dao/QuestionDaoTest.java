package ru.otus.task04.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.otus.task04.builder.QuestionBuilder;
import ru.otus.task04.config.AppProps;
import ru.otus.task04.model.Question;
import ru.otus.task04.parser.Parser;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuestionDaoTest {

    @Mock
    Parser parser;
    @Mock
    AppProps props;
    @Mock
    MessageSource messageSource;

    @Test
    public void test() {
        Map<Integer, Question> expected = new HashMap<>();
        expected.put(1, question(1, "What is the largest city in the UK?"));
        expected.put(2, question(2, "List two closest planets to the Sun"));

        doAnswer(i -> {
            Consumer<Question> consumer = i.getArgument(1, Consumer.class);
            expected.values().stream().forEach(question -> consumer.accept(question));
            return null;
        }).when(parser).parseCsv(any(), any());

        QuestionDao dao = new QuestionDaoImpl(parser, props, messageSource);
        Map<Integer, Question> result = dao.getMapOfQuestions();

        Assertions.assertEquals(expected, result);

        verify(parser, times(1)).parseCsv(any(), any());
    }

    private Question question(int id, String question) {
        QuestionBuilder a = new QuestionBuilder();
        a.setId(id);
        a.setQuestion(question);
        return a.build();
    }
}
