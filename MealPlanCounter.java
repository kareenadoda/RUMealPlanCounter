import java.util.HashMap;
import java.util.Map;

/*This file analyses data of meal swipes spent at various locations at Rutgers 
 * University. You can ask questions and get certain answers. Anyone can use it by downlowding their data of meal swipes from the Dining 
 * Website and eliminating the outlier entries of "Balance Cleared" and "Deposit".
 * Linked List and Hash Map Implementation.
 * 
 * @author: Kareena Doda
 */

public class MealPlanCounter {

    private DiningLocation firstloc;

    /* Constructor */
    public MealPlanCounter() {
        firstloc = null;
    }

    public DiningLocation getFirstLoc() {

        return firstloc;
    }

    public void createStructure(String inputFile) {

        StdIn.setFile(inputFile);
        StdIn.readLine();
       

        // Reads the file one line at a time
        while (StdIn.hasNextLine()) {
            // Reads a single line from input file
            String line = StdIn.readLine();
            
            addToLocations(line);
            
            addToDatafile(line);
        }

    }

    public void addToLocations(String inputline) {
        String[] dataarray = inputline.split("\\s+", 3);  //split at space
        String outletname = dataarray[2];
        outletname = outletname.replaceAll("[^A-Za-z]", ""); //replaces all except letters with ""

        // if location list is empty
        if (firstloc == null) {
            firstloc = new DiningLocation(outletname, null, null);
            return;
        }

        // if not empty, we check whether it exists or not

        // checking if the state exists,
        boolean checker = false;
        DiningLocation ptr = firstloc;

        while (ptr.getNext() != null) { //traverses through location names
            if (ptr.getName().equals(outletname)) {
                checker = true;
                return;
            }

            ptr = ptr.getNext();
        }
        if (ptr.getNext() == null) { //special case of only one element
            if (ptr.getName().contains(outletname)) {
                checker = true;
                return;
            } else {
                DiningLocation n1 = new DiningLocation(outletname, null, null);
                ptr.setNext(n1);
                return;

            }
        }

        // if state doesn't exist
        if (!checker) {
            DiningLocation n1 = new DiningLocation(outletname, null, null);
            ptr.setNext(n1);
        }

    }

    public void addToDatafile(String inputline) {
        String[] dataarray = inputline.split("\\s+", 3);
        String outletname = dataarray[2];
        outletname = outletname.replaceAll("[^A-Za-z]", "");
        String[] DandT = dataarray[0].split("/"); // array to store date and time easily
        double month = Double.parseDouble(DandT[0]);
        double date = Double.parseDouble(DandT[1]);
        // calculation to get time into correct format of doubles without : or Am/PM, and make 0800  into 800, or else it becomes octal nos
        String time1 = dataarray[1];
        time1 = time1.replace(":", "");
        if(time1.charAt(0)=='0'){
            time1=time1.replace(time1.substring(0, 1),"");

        }
        double time2;
        if (time1.contains("PM")) {
            time1 = time1.replace("PM", "");
            time2 = Double.parseDouble(time1);
            time2 = time2 + 1200;
        } else {

            time1 = time1.replace("AM", "");
            time2 = Double.parseDouble(time1);
        }

        // now we have data ready to pass on
        DiningLocation ptr = firstloc;
        while (ptr != null) { // traverse through the locations

            if (ptr.getName().equals(outletname)) { // location found

                if (ptr.getDown() == null) { // if it was empty
                    DataFile d1 = new DataFile(time2, date, month, null);
                    ptr.setDown(d1);
                } else {
                    DataFile df = ptr.getDown(); // if not empty we keep adding instances, instances cannot repeat so we
                                                 // do not need to check..
                    DataFile last = new DataFile(); // ...if they already exist
                    while (df != null) {

                        last = df;
                        df = df.getNext();
                    }

                    DataFile dnew = new DataFile(time2, date, month, null);
                    last.setNext(dnew); // new added to the last one

                }

                // ptr = ptr.getNext();
            }
            ptr = ptr.getNext();
        }
    }

    // How many times have I eaten at -----?
    public int crossLocation(String inputname) {
        int counter = 0;
        boolean checker=false;
        DiningLocation ptr = firstloc;
        while (ptr != null) {
            if (ptr.getName().contains(inputname)) {
                DataFile d1 = ptr.getDown();
                checker=true;
                while (d1 != null) {

                    counter++;
                    d1 = d1.getNext();
                }

            }

            ptr = ptr.getNext();
        }
        if(!checker){
            System.out.println("Location not found!");
        }
        return counter;

    }

    // Where do I eat most in afternoon/morning/evening
    public String crossTime(int timing) {
        DiningLocation ptr = firstloc;
        HashMap<String, Integer> h1 = new HashMap<>();

        while (ptr != null) { // traversing through all location nodes, updating counter for morning
                              // afternoon// evening whichever
            int counter = 0;

            DataFile d1 = ptr.getDown();
            while (d1 != null) {
                if (timing == 1) {
                    // morning
                    if (d1.getTime() >= 800 && d1.getTime() <= 1200) {
                        counter++;
                       
                        
                    }
                }
                else if (timing == 2) { // afternoon
                    if (d1.getTime() >= 1200 && d1.getTime() <= 1500) {
                        counter++;
                        
                       
                    }
                }
                else if (timing == 3) { // evening
                    if (d1.getTime() >= 1500 && d1.getTime() <= 2300) {
                        counter++;
                       
                       
                    }
                }
                else 
                System.out.println("Incorrect input");

                d1 = d1.getNext();
            }

            h1.put(ptr.getName(), counter); // for each location, we have put the number of times eaten there in M/A/E

            ptr = ptr.getNext();
        }
        int max = 0;
        for (Map.Entry<String, Integer> e : h1.entrySet()) {
            if (e.getValue() >= max) {
                max = e.getValue();
                
            }

        }
        String str = "";
        for (Map.Entry<String, Integer> e : h1.entrySet()) {
            if (e.getValue() == max) {
                str = e.getKey();
            }
        }
        return str;
    }

    
}