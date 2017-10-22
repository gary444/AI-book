package bayesianExpertSystem;

import java.util.ArrayList;

public class TargetFact extends Fact {
	
	private ArrayList<FactHypothesis> hypotheses;

	public TargetFact(String factName, ArrayList<FactHypothesis> newHyps){
		
		super(factName);
		hypotheses = newHyps;
	}
	
	// returns a hypothesis of given name
	// if not found, return null
	public FactHypothesis getHypothesis(String hypName){
		
		for (int i = 0; i < hypotheses.size(); i++){
			
			if(hypotheses.get(i).getName().equals(hypName)){
				return hypotheses.get(i);
			}
		}
		
		return null;
	}

	public ArrayList<FactHypothesis> getHypotheses() {
		return hypotheses;
	}
}
