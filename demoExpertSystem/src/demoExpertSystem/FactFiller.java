package demoExpertSystem;

import java.util.ArrayList;
import java.util.Arrays;

public class FactFiller {

	private static ArrayList<DatabaseFact> database = new ArrayList<DatabaseFact>();
	
	public FactFiller(){
		
		populateDatabase();
		//testFacts();
	}
	
	private static void printFact(int index){
		
		System.out.println("fact name = " + database.get(index).getName());
		System.out.println("fact known? = " + database.get(index).isKnown());
		
		if (database.get(index).isKnown())
			System.out.println("value = " + database.get(index).getValue());
		
		//Scanner inScan = new Scanner(System.in);
		
		
		
		/*while(true) {
			
			System.out.print("\nWhat is the value of \"" + database.get(index).getName() + "\" ");
			String newFactVal = inScan.nextLine();
			System.out.println("Value entered is: " + newFactVal);
			
			if (database.get(index).setValue(newFactVal))
				System.out.println("Success! value of " + database.get(index).getName() + " set to: " + database.get(index).getValue());
			
			else 
				System.out.println("Error! invalid value entered.");
		}*/
	}
	
	private static void testFacts(){
		
		for (int i = 0; i < database.size(); i++) {
			
			printFact(i);
		}
	}
	
	
	
	// create a list of facts with their 'allowed values'
	// all string values are lower case!!!!!!!!!!
	private static void populateDatabase(){
				
		DatabaseFact newF;
				
		//environment
		newF = new DatabaseFact("environment", new ArrayList<String>(Arrays.asList("papers"
																					,"manuals", 
																					"documents",
																					"textbooks", 
																					"pictures", 
																					"illustrations", 
																					"photographs", 
																					"diagrams", 
																					"machines", 
																					"buildings",
																					"tools",
																					"numbers",
																					"formulas",
																					"computer programs")));
		database.add(newF);
		
		//stimulus_situation
		newF = new DatabaseFact("stimulus_situation", new ArrayList<String>(Arrays.asList("verbal"
				,"visual", 
				"physical object",
				"symbolic")));
		database.add(newF);
		
		//job
		newF = new DatabaseFact("job", new ArrayList<String>(Arrays.asList("lecturing"
				,"advising", 
				"counselling",
				"building", 
				"repairing", 
				"troubleshooting", 
				"writing", 
				"typing", 
				"drawing", 
				"evaluating",
				"reasoning",
				"investigating")));
		database.add(newF);
		
		//stimulus_response
		newF = new DatabaseFact("stimulus_response", new ArrayList<String>(Arrays.asList("oral"
				,"hands-on", 
				"documented",
				"analytical")));
		database.add(newF);
		
		//feedback
		newF = new DatabaseFact("feedback", new ArrayList<String>(Arrays.asList("required",
				"not required")));
		database.add(newF);
		
		//medium
		newF = new DatabaseFact("medium", new ArrayList<String>(Arrays.asList("workshop"
				,"lecture - tutorial", 
				"videocassette",
				"role-play exercises")));
		database.add(newF);
				
				
	}
	
	public ArrayList<DatabaseFact> getFacts(){
		
		return database;
	}
}
