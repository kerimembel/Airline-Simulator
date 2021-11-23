package aero.smart4aviation.task.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.format.datetime.joda.LocalDateTimeParser;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

    private CustomLocalDateTimeDeserializer(Class<LocalDateTime> t) {
        super(t);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        String date = jsonParser.getText();
        DateTimeFormatter parser = DateTimeFormatter.ISO_DATE_TIME;
        try {
            return LocalDateTime.parse(date,parser);
        } catch (Exception ex) {
            throw new RuntimeException("Cannot Parse Date");
        }
    }
}