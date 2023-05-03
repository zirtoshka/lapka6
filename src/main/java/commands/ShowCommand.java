package commands;


import utilities.CollectionManager;

public class ShowCommand extends Command {
    private  CollectionManager collectionManager;

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public ShowCommand() {
        super("show", "Show collection");
    }

    @Override
    public boolean execute() {
        System.out.println("doesn't work"+getName());
        return true;
    }

}
