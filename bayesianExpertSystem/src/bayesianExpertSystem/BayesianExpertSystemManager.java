package bayesianExpertSystem;

import java.util.ArrayList;
import java.util.Scanner;


public class BayesianExpertSystemManager {
	
	private static ArrayList<DatabaseFact> database = new ArrayList<DatabaseFact>();
	private static ArrayList<Rule> knowledgeBase = new ArrayList<Rule>();
	private static TargetFact targetFact;
	
	//private static int NO_QUESTIONS = 4;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		// fill facts and rules
		FactFiller f = new FactFiller();
		RuleFiller kb = new RuleFiller();
		TargetFactFiller tff = new TargetFactFiller();

		database = f.getFacts();
		knowledgeBase = kb.getKnowledgeBase();
		targetFact = tff.getTargetFact();
		
		System.out.println("Target Fact is " + targetFact.getName());
		
		
		for (int i = 0; i < database.size(); i++){
			System.out.println("Answer received: " + qAndA(i));
		}
		
		
		
		
		
		
	}
	
	// FOR cycle through rules
	// 	if rule is unevaluated and rule has 0 unknown antecedents
	// 		update the hypotheses of the target fact using LS and LN values of that rule
	// 	
	
	private static boolean updateTargetProbabilities(){
		
		// cycle through rules
		for (int i = 0; i < knowledgeBase.size(); i++){
			
			Rule rule = knowledgeBase.get(i);
			// if rule is unevaluated
			if (!rule.isEvaluated()){
				
				//count unknown antecedents
				int noUnknownAntecedents = 0;
				ArrayList<Fact> antecedents = rule.getAntecedents();
				for (int j = 0; j < antecedents.size() && noUnknownAntecedents == 0; j++){
					
					String antName = antecedents.get(j).getName();
					int factTestIndex = findFactIndex(antName);
					
					// if fact is not known...
					if (!database.get(factTestIndex).isKnown()){
						
						noUnknownAntecedents++;
					}
						
				}
				
			 	//if rule is unevaluated and rule has 0 unknown antecedents
				if (noUnknownAntecedents == 0){
					
			 		//update the hypotheses of the target fact using LS and LN values of that rule
					
					String consName = rule.getConsequent().getName();
					double factor;
					double priorProbability = targetFact.getHypotheses(consName).getProbability();
					
					
					// evaluate rule to determine whether to use LN or LS
					if (areRuleCondsMet(rule))
						factor = rule.getLS();
					else
						factor = rule.getLN();
					
					
					targetFact.getHypotheses(consName).setProbability(updateProbability(priorProbability, factor));
				}
			}
		}
		
		//TODO
		return true;
	}
	
	private static boolean areRuleCondsMet(Rule rule){
		
		// TODO evaluate rules to true or false - use some code from first expert system
		
		return true;
	}
	
	private static double updateProbability(double priorProbability, double factor){
		
		double postOdds = priorProbability * factor;
		
		double postProbability = postOdds / (1.0 - postOdds);
		
		return postProbability;
		
	}
	
	// build question text according to index - if index is out of range, return escape string
	// ask question
	// check answer is a valid answer - if not repeat question
	// return string
	private static String qAndA(int factDatabaseIndex){
		
		String qText;
		
		// check fact index is in range
		if (factDatabaseIndex < database.size()
				&& factDatabaseIndex <= 0){
			
			// build question with permitted values
			ArrayList<String> possVals = database.get(factDatabaseIndex).getPossValues();
			qText = "What is the " + database.get(factDatabaseIndex).getName() + " today (";
			
			for (int i = 0; i < possVals.size(); i++){
				qText += possVals.get(i);
				if(i < possVals.size() - 1)
					qText += "/";
			}
			
			qText += "):";
			
		}
		else 
			return "Q";
		
		//ask question
		System.out.println(qText);
		Scanner inScan = new Scanner(System.in);
		String ansText = inScan.nextLine();
		
		//compare answer to possible fact values
		while (!database.get(factDatabaseIndex).setValue(ansText)){
			System.out.println("Invalid valid for this field. Please try again.");
			System.out.println(qText);
			ansText = inScan.nextLine();
		}
		
		return ansText;
		
	}
	
	// given a string which should be the name of a fact in the database
	// searches through database to find index of the fact with given name
	// returns index found
	// returns -1 if not found
	private static int findFactIndex(String factNameToSearch) {
		
		for (int i = 0; i < database.size(); i++){
			
			if(database.get(i).getName().equals(factNameToSearch))
				return i;
		}
		
		//if match not found, return -1
		return -1;
	}

}
