public class DiningLocation { 
    

/**
 * This class contains names of various Dining locations at Rutgers NB as nodes
 * 
 */
    public String name; // location's name
    public DiningLocation next; // link to the next location in the list
    public DataFile down; // link to all the instances of this location


    /*
     * Default constuctor: creates a node with null name and references
     */
    public DiningLocation() {
        this.name = null;
        this.next = null;
        this.down = null;
    }

    /**
     * Three argument constructor: creates a node with name, next, and down
     * references.
     * 
     */
    public DiningLocation(String name, DiningLocation next, DataFile down) {
        this.name = name;
        this.next = next;
        this.down = down;
    }

    /**
     * Updates instance variable name.
     * 
     * @param name the new name to update with
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of this node
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the next reference of this node to parameter
     * 
     * @param next the new next node
     */
    public void setNext(DiningLocation next) {
        this.next = next;
    }

    /**
     * Obtains the next node
     * 
     * @return a StateNode reference to the next node
     */
    public DiningLocation getNext() {
        return next;
    }

    /**
     * Updates the down reference of this node
     * 
     * @param down the new down reference - MUST be a CountyNode
     */
    public void setDown(DataFile down) {
        this.down = down;
    }

    /**
     * Returns the down reference (CountyNode) of this node
     * 
     * @return the CountyNode pointing down to this node
     */
    public DataFile getDown() {
        return down;
    }

    /*
     * Returns true if StateNodes contain the same name
     * 
     * @param other object to compare
     * @return true if other contains the name name
     */
    public boolean equals (Object other) {

        if ( other instanceof DiningLocation ) {
            DiningLocation o = (DiningLocation) other;
            return this.getName().equals(o.getName());
        } else {
            return false;
        }
    }
}
    

