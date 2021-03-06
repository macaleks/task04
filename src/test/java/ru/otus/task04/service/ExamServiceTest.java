package ru.otus.task04.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.task04.builder.AnswerBuilder;
import ru.otus.task04.builder.QuestionBuilder;
import ru.otus.task04.dao.AnswerDao;
import ru.otus.task04.dao.QuestionDao;
import ru.otus.task04.model.Answer;
import ru.otus.task04.model.Question;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExamServiceTest {
    @Mock
    AnswerDao answerDao;
    @Mock
    QuestionDao questionDao;
    @Mock
    ScannerService scanner;
    @Mock
    MessageService service;

    @Test
    public void test() {
        ExamService examService = new ExamService(answerDao, questionDao, scanner, service);

        Map<Integer, Question> questionDaoReturn = new HashMap<>();
        questionDaoReturn.put(1, question(1, "question1"));
        questionDaoReturn.put(2, question(2, "question2"));
        questionDaoReturn.put(3, question(3, "question3"));
        doReturn(questionDaoReturn).when(questionDao).getMapOfQuestions();

        Map<Integer, List<Answer>> answerDaoReturn = new HashMap<>();
        answerDaoReturn.put(1, Arrays.asList(answer(1, "answer1", true)));
        answerDaoReturn.put(2, Arrays.asList(answer(2, "answer1", true),
                answer(2, "answer2", true)));
        answerDaoReturn.put(3, Arrays.asList(answer(3, "answer1", true),
                answer(3, "answer2", true),
                answer(3, "answer3", true)));
        doReturn(answerDaoReturn).when(answerDao).getMapOfAnswers();

        doReturn(0, 0, 1, 0, 1, 2).when(scanner).nextInt();

        doNothing().when(service).writeMessage(any(), any());
        doNothing().when(service).write(any(), any());

        doAnswer(i -> {
            String name = i.getArgument(1);
            String surname = i.getArgument(2);
            return String.format("%s %s has ", name, surname);
        }).when(service).getMessage(eq("studentHas"), any(), any());
        doReturn("passed").when(service).getMessage("passed");

        assertThat(examService.testStudent("Olaf", "Swenson")).isEqualTo("Olaf Swenson has passed");

    }

    private Question question(int id, String question) {
        QuestionBuilder a = new QuestionBuilder();
        a.setId(id);
        a.setQuestion(question);
        return a.build();
    }

    private Answer answer(int questionId, String answer, boolean correct) {
        AnswerBuilder a = new AnswerBuilder();
        a.setId(questionId);
        a.setAnswer(answer);
        a.setCorrect(correct);
        return a.build();
    }
}
