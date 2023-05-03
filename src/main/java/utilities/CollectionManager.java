package utilities;


import IO.ConsoleManager;
import data.StudyGroup;
import exceptions.NullCollectionException;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

public class CollectionManager {
    private ArrayDeque<StudyGroup> studyGroupCollection;
    private Set<Integer> idSet = new HashSet<>();

    private Integer newId = 1;
    private final int SIZE_EMPTY = 0;
    private final String emptyCollection = "Collection is empty";
    private final FileManager fileManager;

    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;


    public CollectionManager(FileManager fileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;
    }

    public void createCollection() {
        this.studyGroupCollection = new ArrayDeque<StudyGroup>();
    }


    public void loadFromFile() {
        try {
            this.studyGroupCollection = fileManager.loadFromFile();
            if (collectionSize() != 0) {
                for (StudyGroup stg : studyGroupCollection) {
                    if (stg.getId() == StudyGroup.wrongId) {
                        studyGroupCollection.remove(stg);
                    } else {
                        idSet.add(stg.getId());
                    }
                }
            }
            if (studyGroupCollection == null) {
                createCollection();
            }
        } catch (FileNotFoundException e) {
            ConsoleManager.printError(e);
        }

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

    public void addToCollection(StudyGroup studyGroupFromUser) {
        studyGroupCollection.add(studyGroupFromUser);
        lastInitTime = LocalDateTime.now();
    }

    public void addToCollectionIfMax(StudyGroup studyGroupFromUser) {
        if (studyGroupFromUser.getStudentsCount() > getMaxNumberInGroup()) {
            studyGroupCollection.add(studyGroupFromUser);
            lastInitTime = LocalDateTime.now();
        }
    }

    public LocalDateTime getLastInitTime() {
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
            ConsoleManager.printError("Collection is empty");
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
            ConsoleManager.printError("Collection is empty");
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
            return "Collection is empty(((";
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
