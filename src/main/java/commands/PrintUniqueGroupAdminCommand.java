package commands;


import utilities.CollectionManager;

public class PrintUniqueGroupAdminCommand extends Command {
    private  CollectionManager collectionManager;

    public PrintUniqueGroupAdminCommand() {
        super("print_unique_group_admin", "display the unique values of the groupAdmin field of all items in the collection");
    }
    public void setCollectionManager(CollectionManager collectionManager){
        this.collectionManager=collectionManager;
    }

    @Override
    public boolean execute() {
        System.out.println("doesn't work"+getName());
        return true;

    }
}



