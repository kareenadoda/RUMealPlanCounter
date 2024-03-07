public class Driver {
    
/**
 * hopefully this works bros
 * */

    private static MealPlanCounter mealplanner;
    
    public static void main(String[] args) {
        String[] methods = {"createStructure", "crossLocation", "crossTime"};
		String[] options = {"Test a new input file", "Test another method on the same file", "Quit"};
		int controlChoice = 0;
		
		do {
			StdOut.print("Enter input file => ");
			String inputFile = StdIn.readLine();
            mealplanner = new MealPlanCounter();
			do {
				StdOut.println("\nWhat method would you like to test?");
				for (int i = 0; i < 3; i++) {
					StdOut.printf("%d. %s\n", i+1, methods[i]);
				}
				StdOut.print("Enter a number => ");
				int choice = Integer.parseInt(StdIn.readLine());
                
				switch (choice) {
					case 1:
                        testCreateLinkedList(inputFile);
						break;
					case 2:
                        System.out.print("Enter location where you want to check how many times you've eaten here => ");
                        System.out.print("Enter location(Livingston, Busch, INFH, Cafe West, Atrium) => ");
                        String location = StdIn.readLine();
						testcrossLocation(location);
						break;
					case 3:
                        System.out.println("Enter 1 to know where you eat most in the mornings, 2 for afternoons and 3 for evenings");
                        int timimg = Integer.parseInt(StdIn.readLine());
                        testcrossTime(timimg);
						break;
					
					default:
						StdOut.println("Not a valid option!");
				

            
            }
                StdIn.resetFile();
                System.out.println();
				StdOut.println("What would you like to do now?");
				for (int i = 0; i < 3; i++) {
					StdOut.printf("%d. %s\n", i+1, options[i]);
				}
				StdOut.print("Enter a number => ");
				controlChoice = Integer.parseInt(StdIn.readLine());
			} while (controlChoice == 2);
		} while (controlChoice == 1);
    }

    // create initial 2-layered linked list
    private static void testCreateLinkedList(String filename) {
        StdIn.setFile(filename);
        mealplanner.createStructure(filename);
       //doesnt come out of it
        StdIn.resetFile();

        System.out.print("Would you like the list to be printed? [Y/N]: ");
        String readInput = StdIn.readLine();
        if (readInput.equals("Y")){
            System.out.println("Here is the list of locations you have eaten at ");
            for (DiningLocation ptr =  mealplanner.getFirstLoc(); ptr != null; ptr = ptr.getNext()) {
                StdOut.println("+ " + ptr.getName());
                int counter=0;
                for (DataFile d1 = ptr.getDown(); d1 != null; d1 = d1.getNext()) {
                    counter++;
                   
                }
                StdOut.print(" [" + counter + " meal(s)]");
                StdOut.println();
                StdOut.println();
            }
        }

        return;
    }

    // return the # of times you;ve eaten at a particular location by calling crosslocation method
    private static void testcrossLocation(String location) {
        int numOfTimes = mealplanner.crossLocation(location);
        System.out.println("You have eaten at " + location + " " +numOfTimes + " number of times"); 
    }


    // returns the location where you have eaten the most in morning/afternoon/evening by calling crossTime method
    private static void testcrossTime(int timing) {
        
        String locationOutput = mealplanner.crossTime(timing);
        String timeofday="";
        if(timing==1){
            timeofday="mornings";
        }
        if(timing==2){
            timeofday="the afternoon";
        }if(timing==3){
            timeofday="evenings";
        }
        System.out.println("In "+ timeofday+", you have eaten the most at "+ locationOutput);
    }
   
}
   

