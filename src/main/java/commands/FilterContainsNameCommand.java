package commands;

import utilities.CollectionManager;

public class FilterContainsNameCommand extends Command {
    private  CollectionManager collectionManager;

    public FilterContainsNameCommand( ) {
        super("filter_contains_name <name>", "display elements whose name field value contains the given substring");
    }
    public void setCollectionManager(CollectionManager collectionManager){
        this.collectionManager=collectionManager;
    }

    @Override
    public boolean execute(){
        System.out.println("doesn't work command "+getName());
        return true;
    }
}
