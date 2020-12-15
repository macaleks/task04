package ru.otus.task04.parser;

import org.springframework.stereotype.Service;
import ru.otus.task04.builder.QuestionBuilder;
import ru.otus.task04.model.Question;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Service
public class QuestionParserImpl extends CsvParser implements Parser<Question> {
    private final Map<String, BiConsumer<QuestionBuilder, String>> mapper = new HashMap<>();

    public QuestionParserImpl() {
        this.mapper.put("id", (o, v) -> o.setId(Integer.valueOf(v)));
        this.mapper.put("question", QuestionBuilder::setQuestion);
    }

    @Override
    public void parseCsv(String filename, Consumer<Question> consumer) {
        parseCsv(filename, QuestionBuilder::new, mapper, consumer);
    }
}
