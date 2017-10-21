package demoExpertSystem;

import java.util.ArrayList;

// note: each rule currently allows only one consequent
public class Rule {

	private ArrayList<ConditionalFact> antecedents;
	private ConditionalFact consequent;
	private String logicType;
	
	//constructor
	public Rule(ArrayList<ConditionalFact> newAntecedents,
				ConditionalFact newConsequent,
				String newLogicType){
		
		antecedents = newAntecedents;
		consequent = newConsequent;
		logicType = newLogicType;
	}
	
	public ArrayList<ConditionalFact> getAntecedents(){
		
		return antecedents;
	}
	
	public ConditionalFact getConsequent(){
		return consequent;
	}
	
	public String getLogicType(){
		return logicType;
	}
	
}
