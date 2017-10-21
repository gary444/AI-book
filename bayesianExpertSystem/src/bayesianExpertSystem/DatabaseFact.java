package bayesianExpertSystem;

import java.util.ArrayList;

// database fact is a version of Fact which is defined as part of the database#
// it adds a list of possible values which are defined when the constructor is called
// when a new value is assigned it checks the new value against its possible values
public class DatabaseFact extends Fact {
	
	private ArrayList<String> permittedValues;
	private boolean known = false;
	
	// constructor
	// fact should be initialized with a list of values
	public DatabaseFact(String factName, ArrayList<String> values){
		
		super(factName);
		permittedValues = values;
	}
	
	//returns true if the fact is known
	public boolean isKnown(){
		return known;
	}
	
	// attempt to set a value
	// return true if newValue is found in arrayList of possible values, and set known to true
	public boolean setValue(String newValue){
		
		for (int i = 0; i < permittedValues.size(); i++){
			
			if (newValue.equals(permittedValues.get(i))){
				super.setValue(newValue);
				known = true;
				return true;
			}
		}
		
		return false;
	}
	
	
	// when given a conditional fact with name, value and relationship
	// checks names match although this should already have been checked - exception?
	// calls internal updateValue method to update value whilst checking against a list of possible values
	// TODO could change positiveRelationship var if implemented in Fact superclass
	// return true if successful, false otherwise
	public boolean updateFactWith(Fact f){
		
		//check fact names match
		if (!super.getName().equals(f.getName()))
			return false; // TODO exception here???
		
		
		return setValue(f.getValue());
	}
	
	public ArrayList<String> getPossValues(){
		return permittedValues;
	}
}