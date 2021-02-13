package ru.otus.task04.parser;

import java.util.function.Consumer;

public interface Parser<T> {

    void parseCsv(String filename, Consumer<T> consumer);
}
