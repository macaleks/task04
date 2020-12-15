package ru.otus.task03.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.task03.config.AppProps;

@Service
public class MessageService {

    private final MessageSource messageSource;
    private final AppProps props;

    public MessageService(MessageSource messageSource, AppProps props) {
        this.messageSource = messageSource;
        this.props = props;
    }

    public void write(String message, Object... args) {
        System.out.println(String.format(message, args));
    }

    public void writeMessage(String message, Object... args) {
        System.out.println(getMessage(message, args));
    }

    public String getMessage(String message, Object... args) {
        return String.format(messageSource.getMessage(message, null, props.getLocale()), args);
    }

    public void emptyLine() {
        System.out.println();
    }
}
