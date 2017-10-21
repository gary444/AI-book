package bayesianExpertSystem;

public class Fact {

	private String name;
	private String value = null;
	
	// constructor
	// fact should be initialized with a name
	public Fact(String factName){
		
		name = factName;
		
	}
	
	public Fact(String factName, String factValue){
		this(factName);
		value = factValue;
	}
	
	// returns name of the field the fact represents
	public String getName(){
		return name;
	}
	

	//set new value
	// super class version will always return true
	public boolean setValue(String newValue){
		
		value = newValue;
		
		return true;
	}
	
	// returns the value of this fact as a string
	public String getValue(){
		
		return value;
	}
}


