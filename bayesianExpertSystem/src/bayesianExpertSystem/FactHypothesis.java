package bayesianExpertSystem;

public class FactHypothesis {

	private String name;
	private double probability;
	
	public FactHypothesis(String name, double probability){
		this.name = name;
		setProbability(probability);
	}
	
	//sets new probability
	// returns true if prob. is in correct range of 0 < p < 1
	public boolean setProbability(double newProbability){
		if (newProbability > 0.0 && newProbability < 1.0){
			probability = newProbability;
			return true;
		}
		else 
			return false;
			
	}
	
	public String getName(){return name;}
	
	public double getProbability(){return probability;}
	
	
}
