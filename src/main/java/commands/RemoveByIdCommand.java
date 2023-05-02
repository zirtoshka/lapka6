package commands;


import utilities.CollectionManager;

public class RemoveByIdCommand extends Command {
    private  CollectionManager collectionManager;

    public RemoveByIdCommand() {
        super("remove_by_id <id>", "remove element by id");

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
