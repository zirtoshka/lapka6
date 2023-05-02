package commands;


import data.StudyGroup;
import utilities.CollectionManager;

public class AddCommand extends Command {
    private CollectionManager collectionManager;
    private  StudyGroup argGroupr;

    public AddCommand() {
        super("add", "add a new element to the collection");
    }
    public void setCollectionManager(CollectionManager manager){
        this.collectionManager=manager;
    }
    public void setArgGroupr(StudyGroup argGroupr){
        this.argGroupr=argGroupr;
    }

    @Override
    public boolean execute() {
        collectionManager.addToCollection(argGroupr);
        return true;
    }
}
