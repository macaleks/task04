package ru.otus.task04.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.otus.task04.builder.AnswerBuilder;
import ru.otus.task04.config.AppProps;
import ru.otus.task04.model.Answer;
import ru.otus.task04.parser.Parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AnswerDaoTest {

    @Mock
    Parser parser;
    @Mock
    AppProps props;
    @Mock
    MessageSource messageSource;

    @Test
    public void test() {
        Map<Integer, List<Answer>> expected = new HashMap<>();
        expected.put(1, Arrays.asList(answer(1, "Manchester", false),
                answer(1, "Lincoln", false)));
        expected.put(2, Arrays.asList(answer(2, "Earth", false),
                answer(2, "Mercury", true)));


        doAnswer(i -> {
            Consumer<Answer> consumer = i.getArgument(1, Consumer.class);
            expected.values().stream().flatMap(List::stream).forEach(answer -> consumer.accept(answer));
            return null;
        }).when(parser).parseCsv(any(), any());

        AnswerDao dao = new AnswerDaoImpl(parser, props, messageSource);
        Map<Integer, List<Answer>> result = dao.getMapOfAnswers();

        Assertions.assertEquals(expected, result);

        verify(parser, times(1)).parseCsv(any(), any());
    }

    private Answer answer(int questionId, String answer, boolean correct) {
        AnswerBuilder a = new AnswerBuilder();
        a.setId(questionId);
        a.setAnswer(answer);
        a.setCorrect(correct);
        return a.build();
    }
}
