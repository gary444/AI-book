package demoExpertSystem;

// conditional fact is a version of fact which is used to make up rules
// these facts are not known, but are tested against the database to evaluate rules
// contains a fact name and a value (from superclass), and also a relationship between them (IS or IS NOT)
public class ConditionalFact extends Fact {
	
	//TODO transfer this variable and associated methods to superclass
	private static boolean positiveRelationship;

	// should be initiated with a name, value and relationship
	ConditionalFact(String factName, String value, boolean isPositiveRelationship){
		
		super(factName);
		super.setValue(value);
		positiveRelationship = isPositiveRelationship;
		
	}
	
	
	// returns true if relationship is positive
	public boolean isRelationshipPositive(){
		return positiveRelationship;
	}
}
