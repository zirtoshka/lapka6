package parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import data.StudyGroup;


import java.io.*;
import java.util.ArrayDeque;

public class ReadYAMLParser {

    public ArrayDeque<StudyGroup> read(String path) throws IOException {
        File file = new File(path);
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.registerModule(new JavaTimeModule());
        ArrayDeque<StudyGroup> collectionFromFile = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(ArrayDeque.class, StudyGroup.class));
        return collectionFromFile;
    }
}

