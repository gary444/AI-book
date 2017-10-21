package bayesianExpertSystem;

import java.util.ArrayList;
import java.util.Arrays;

public class FactFiller {

	private static ArrayList<DatabaseFact> database = new ArrayList<DatabaseFact>();
	
	public FactFiller(){
		
		populateDatabase();
		//testFacts();
	}
	
	/*
	private static void printFact(int index){
		
		System.out.println("fact name = " + database.get(index).getName());
		System.out.println("fact known? = " + database.get(index).isKnown());
		
		if (database.get(index).isKnown())
			System.out.println("value = " + database.get(index).getValue());
		
	}

	
	private static void testFacts(){
		
		for (int i = 0; i < database.size(); i++) {
			
			printFact(i);
		}
	}
	
	*/
	
	// create a list of facts with their 'allowed values'
	// all string values are lower case!!!!!!!!!!
	private static void populateDatabase(){
				
		DatabaseFact newF;
				
	
		newF = new DatabaseFact("temperature", new ArrayList<String>(Arrays.asList("warm",
																					"cold")));
		database.add(newF);
		
		
		newF = new DatabaseFact("rainfall", new ArrayList<String>(Arrays.asList("low",
				"high")));
		database.add(newF);
		
		newF = new DatabaseFact("cloud cover", new ArrayList<String>(Arrays.asList("overcast",
				"clear")));
		database.add(newF);
		
		newF = new DatabaseFact("weather", new ArrayList<String>(Arrays.asList("rain",
				"dry")));
		database.add(newF);
				
	}
	
	public ArrayList<DatabaseFact> getFacts(){
		
		return database;
	}
}
