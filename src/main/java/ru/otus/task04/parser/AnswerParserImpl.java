package ru.otus.task04.parser;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;
import ru.otus.task04.builder.AnswerBuilder;
import ru.otus.task04.model.Answer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Service
public class AnswerParserImpl extends CsvParser implements Parser<Answer> {
    private final Map<String, BiConsumer<AnswerBuilder, String>> mapper = new HashMap<>();

    public AnswerParserImpl() {
        this.mapper.put("question_id", (o, v) -> o.setId(Integer.valueOf(v)));
        this.mapper.put("answer", AnswerBuilder::setAnswer);
        this.mapper.put("correct", (o, v) -> o.setCorrect(BooleanUtils.toBoolean(v)));
    }

    @Override
    public void parseCsv(String filename, Consumer<Answer> consumer) {
        parseCsv(filename, AnswerBuilder::new, mapper, consumer);
    }
}
