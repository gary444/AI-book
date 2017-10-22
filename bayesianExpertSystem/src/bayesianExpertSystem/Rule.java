package bayesianExpertSystem;

import java.util.ArrayList;

// note: each rule currently allows only one consequent
public class Rule {

	private ArrayList<Fact> antecedents;
	private Fact consequent;
	private String logicType;
	private double LS;
	private double LN;
	private boolean evaluated;
	
	//constructor
	public Rule(ArrayList<Fact> newAntecedents,
				Fact newConsequent,
				String newLogicType,
				double newLS,
				double newLN){
		
		antecedents = newAntecedents;
		consequent = newConsequent;
		logicType = newLogicType;
		LS = newLS;
		LN = newLN;
		
		evaluated = false;
	}
	
	public ArrayList<Fact> getAntecedents(){
		
		return antecedents;
	}
	
	public Fact getConsequent(){
		return consequent;
	}
	
	public String getLogicType(){
		return logicType;
	}
	
	public double getLS(){return LS;}

	public double getLN(){return LN;}
	
	public boolean isEvaluated(){return evaluated;}

	public void setEvaluated(){evaluated = true; }
	
}
