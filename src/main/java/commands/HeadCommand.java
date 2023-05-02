package commands;
import utilities.CollectionManager;


public class HeadCommand extends Command {
    private  CollectionManager collectionManager;

    public HeadCommand() {
        super("head", "print the first element of the collection");

    }
    public void setCollectionManager(CollectionManager collectionManager){
        this.collectionManager=collectionManager;
    }

    @Override
    public boolean execute() {
        System.out.println("doesn't work" +getName());
        return true;}

}
