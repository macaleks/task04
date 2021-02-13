package ru.otus.task04.service;

import org.springframework.stereotype.Service;
import ru.otus.task04.dao.AnswerDao;
import ru.otus.task04.dao.QuestionDao;
import ru.otus.task04.model.Answer;
import ru.otus.task04.model.Question;
import ru.otus.task04.model.Student;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ExamService {

    private final AnswerDao answerDao;
    private final QuestionDao questionDao;
    private final ScannerService scanner;
    private final MessageService service;

    public ExamService(AnswerDao answerDao, QuestionDao questionDao, ScannerService scanner, MessageService service) {
        this.answerDao = answerDao;
        this.questionDao = questionDao;
        this.scanner = scanner;
        this.service = service;
    }

    public String testStudent(String name, String surname) {
        Map<Integer, List<Answer>> allAnswers = answerDao.getMapOfAnswers();
        Map<Integer, Question> allQuestions = questionDao.getMapOfQuestions();
        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);

        service.writeMessage("askToAnswerQuestions", student.getName(), student.getSurname());
        service.emptyLine();
        for (Question question : allQuestions.values()) {
            service.write("%d. %s", question.getId(), question.getQuestion());

            List<Answer> answers = allAnswers.get(question.getId());
            long correctAnswers = answers.stream().filter(Answer::isCorrect).count();
            service.writeMessage("selectCorrectAnswers", correctAnswers);
            service.emptyLine();

            for (int i = 0; i < answers.size(); i++) {
                service.write("%d. %s", i, answers.get(i).getAnswer());
            }

            Set<Answer> respond = new HashSet<>();
            for (int i = 0; i < correctAnswers; i++) {
                int var = scanner.nextInt();
                //Check that it is not out of range
                if (var >= 0 && var < answers.size()) {
                    respond.add(answers.get(var));
                }
            }

            student.addQuestionResult(question, CheckAnswerService.check(respond, correctAnswers));
        }
        String result = service.getMessage("studentHas", student.getName(), student.getSurname());
        if (student.checkIfPassed()) {
            result += service.getMessage("passed");
        } else {
            result += service.getMessage("notPassed");
        }
        return result;
    }
}
