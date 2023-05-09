package utilities;


import IO.ConsoleManager;
import data.Semester;
import data.StudyGroup;
import exceptions.NullCollectionException;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

import static data.Semester.DEFAULT_SEMESTER;
import static data.StudyGroup.wrongId;

public class CollectionManager {
    private ArrayDeque<StudyGroup> studyGroupCollection;
    private Set<Integer> idSet = new HashSet<>();

    private Integer newId = 1;
    private final int SIZE_EMPTY = 0;
    private final String emptyCollection = "Collection is empty";
    private final FileManager fileManager;

    private static LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;


    public CollectionManager(FileManager fileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;
    }

    public void createCollection() {
        this.studyGroupCollection = new ArrayDeque<StudyGroup>();
    }

    public FileManager getFileManager() {
        return this.fileManager;
    }


    public void writeToFile() {
        fileManager.write(this.studyGroupCollection);
    }

    public ArrayDeque<StudyGroup> getStudyGroupCollection() {
        return studyGroupCollection;
    }

    public Integer generateId() {
        while (!idSet.add(newId)) {
            newId++;
        }
        return newId;
    }

    public String addToCollection(StudyGroup studyGroupFromUser) {
        if (studyGroupFromUser.getId().equals(wrongId)) {
            studyGroupFromUser.setId(generateId());
        }
        studyGroupCollection.add(studyGroupFromUser);
        lastInitTime = LocalDateTime.now();
        return "StudyGroup added successfully";
    }

    public String addToCollectionIfMax(StudyGroup studyGroupFromUser) {
        if (studyGroupFromUser.getStudentsCount() > getMaxNumberInGroup()) {
            if (studyGroupFromUser.getId().equals(wrongId)) {
                studyGroupFromUser.setId(generateId());
            }
            studyGroupCollection.add(studyGroupFromUser);
            lastInitTime = LocalDateTime.now();
            return "StudyGroup added successfully";
        }
        return "The StudyGroup is less than maximum.";
    }

    public String printFieldDescendingSemester() {
        String res = "";
        Set<Semester> semesterSet = new HashSet<>();
        studyGroupCollection.stream().forEach(studyGroup -> semesterSet.add(studyGroup.getSemesterEnum()));
        for (Semester type : Semester.values()) {
            if (!type.equals(DEFAULT_SEMESTER) && semesterSet.contains(type)) {
                res += type.name() + "\n";
            }
        }
        return res;
    }

    public String printUniqueAdmin() {
        Set<String> nameSet1 = new HashSet<>();
        Set<String> nameSet2 = new HashSet<>();
        for (StudyGroup sg : getStudyGroupCollection()
        ) {
            if (!(sg.getGroupAdmin() == null)) {
                if (!nameSet1.add(sg.getGroupAdmin().getName())) {
                    nameSet2.add(sg.getGroupAdmin().getName());
                }
            }
        }
        for (String a : nameSet2
        ) {
            nameSet1.remove(a);
        }
        return nameSet1.toString();
    }

    public static LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    public String collectionType() {
        try {
            if (studyGroupCollection.isEmpty()) throw new NullCollectionException();
            return studyGroupCollection.getClass().getName();
        } catch (NullCollectionException | NullPointerException e) {
            ConsoleManager.printError(emptyCollection);
        }
        return emptyCollection;
    }

    public int collectionSize() {
        try {
            if (studyGroupCollection == null) throw new NullCollectionException();
            return studyGroupCollection.size();
        } catch (NullCollectionException e) {
            return SIZE_EMPTY;
        }

    }

    public void loadFromFile() {
        try {
            this.studyGroupCollection = fileManager.loadFromFile();
            if (collectionSize() != 0) {
                for (StudyGroup stg : studyGroupCollection) {
                    if (stg.getId() == wrongId) {
                        studyGroupCollection.remove(stg);
                    } else {
                        idSet.add(stg.getId());

                    }
                }
                Iterator<Integer> iterator = idSet.iterator();
                while (iterator.hasNext()) { //checking
                    System.out.println(iterator.next());
                }
            }
            if (studyGroupCollection == null) {
                createCollection();
            }
        } catch (FileNotFoundException e) {
            ConsoleManager.printError(e);
        }
    }

    public String printInfo() {
        return "Collection info:\n" +
                " Type: " + collectionType() +
                "\n Quantity: " + collectionSize() +
                "\n Last save: " + lastSaveTime +
                "\n Last enter: " + lastInitTime;
    }

    public void clearCollection() {
        try {
            if (studyGroupCollection == null) throw new NullCollectionException();
            studyGroupCollection.clear();
        } catch (NullCollectionException e) {
        }

    }

    public void saveCollection() {
        this.writeToFile();
        lastSaveTime = LocalDateTime.now();
    }

    public String headOfCollection() {
        try {
            if (studyGroupCollection.isEmpty()) throw new NullCollectionException();
            return studyGroupCollection.getFirst().toString();
        } catch (NullCollectionException e) {
            ConsoleManager.printError(emptyCollection);
        }
        return emptyCollection;
    }

    public StudyGroup getById(Integer id) {
        for (StudyGroup studyGroup : studyGroupCollection) {
            if (studyGroup.getId().equals(id)) {
                return studyGroup;
            }
        }
        return null;
    }

    public void removeFromCollection(StudyGroup studyGroup) {
        studyGroupCollection.remove(studyGroup);
    }

    public void removeById(StudyGroup studyGroup) {
        idSet.remove(studyGroup.getId());
        studyGroupCollection.remove(studyGroup);
    }

    public int getMaxNumberInGroup() {
        int res = -1;
        for (StudyGroup group : studyGroupCollection) {
            if (group.getStudentsCount() > res) {
                res = group.getStudentsCount();
            }
        }
        return res;
    }

    @Override
    public String toString() {
        if (studyGroupCollection.isEmpty()) {
            return emptyCollection + "(((";
        }
        StringBuilder info = new StringBuilder();
        for (StudyGroup studyGroup : studyGroupCollection) {
            info.append(studyGroup.toString());
            if (studyGroup != studyGroupCollection.getLast()) {
                info.append("\n\n");
            }
        }
        return info.toString();
    }


}
