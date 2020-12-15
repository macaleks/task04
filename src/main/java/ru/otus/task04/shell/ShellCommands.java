package ru.otus.task04.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.task04.service.ExamService;

@ShellComponent
public class ShellCommands {

    private String name;
    private String surname;
    private final ExamService examService;

    public ShellCommands(ExamService examService) {
        this.examService = examService;
    }

    @ShellMethod(value = "Enter your name", key = {"n", "name"})
    public void getName(String name) {
        this.name = name;
    }

    @ShellMethod(value = "Enter your surname", key = {"s", "surname"})
    public void getSurname(String surname) {
        this.surname = surname;
    }

    @ShellMethod(value = "print fio", key = "fio")
    public String getFIO() {
        return name + " " + surname;
    }

    @ShellMethod(value = "take an exam", key = {"e", "exam"})
    @ShellMethodAvailability(value = "isExamAvailable")
    public String takeExam() {
        return examService.testStudent(name, surname);
    }

    private Availability isExamAvailable() {
        return name == null || surname == null ? Availability.unavailable("Enter your name and surname")
                : Availability.available();
    }
}
