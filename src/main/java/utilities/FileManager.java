package utilities;




import IO.ConsoleManager;
import data.StudyGroup;
import exceptions.NotNullException;
import parser.ReadYAMLParser;
import parser.WriteYAMLParses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;

public class FileManager {
    private final String path;
    private final String fileName;

    public FileManager(String fileName){
        this.fileName = fileName;
        path = System.getenv("PWD")+"/"+fileName;
    }
    public boolean isFileEmpty() {
        File file = new File(path);
        return file.length() == 0;
    }
    public ArrayDeque<StudyGroup> loadFromFile() throws FileNotFoundException {
        ReadYAMLParser yaml = new ReadYAMLParser();
        ArrayDeque<StudyGroup> studyGroupCollection = null;
        try {
            studyGroupCollection = yaml.read(path);
        }catch (IOException e){
            ConsoleManager.printError("Unknown issues with file. Now it's clean and collection is empty");
            PrintWriter userFile = new PrintWriter(path);
            userFile.print("");
            userFile.close();
        }
        return studyGroupCollection;
    }
    public void write(ArrayDeque<StudyGroup> studyGroupCollection){
        try {
            if(fileName.isEmpty()) throw new NotNullException();
            WriteYAMLParses writeYAMLParses = new WriteYAMLParses();
            writeYAMLParses.write(studyGroupCollection,path);
        } catch (NotNullException | IOException e){
            ConsoleManager.printError("No access to write");
        }
        }
    }



