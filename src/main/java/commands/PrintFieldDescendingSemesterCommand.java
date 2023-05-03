package commands;

import utilities.CollectionManager;

public class PrintFieldDescendingSemesterCommand extends Command {
    private CollectionManager collectionManager;

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public PrintFieldDescendingSemesterCommand( ) {
        super("print_field_descending_semester_enum", "display the values of the semesterEnum field of all elements in descending order");
    }

    @Override
    public boolean execute() {
        System.out.println("dosn'twork"+getName());
        return true;
    }
}
