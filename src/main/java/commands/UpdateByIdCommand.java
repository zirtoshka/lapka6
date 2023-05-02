package commands;


import utilities.CollectionManager;

public class UpdateByIdCommand extends Command {
    private CollectionManager collectionManager;
    public UpdateByIdCommand() {
        super("update_by_id <id>", "update element by id");

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
