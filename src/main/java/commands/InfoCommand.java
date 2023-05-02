package commands;


import utilities.CollectionManager;

public class InfoCommand extends Command {
    private CollectionManager collectionManager;

    public InfoCommand() {
        super("info", "print information about the collection");
    }
    public void setCollectionManager(CollectionManager collectionManager){
        this.collectionManager=collectionManager;
    }

    @Override
    public boolean execute() {

        return true;
    }
}
