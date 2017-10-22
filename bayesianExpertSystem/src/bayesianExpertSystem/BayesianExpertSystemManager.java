package bayesianExpertSystem;

import java.util.ArrayList;
import java.util.Scanner;


public class BayesianExpertSystemManager {
	
	private static ArrayList<DatabaseFact> database = new ArrayList<DatabaseFact>();
	private static ArrayList<Rule> knowledgeBase = new ArrayList<Rule>();
	private static TargetFact targetFact  = null;
	
	//private static int NO_QUESTIONS = 4;

	public static void main(String[] args) {
		
		
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
			updateTargetProbabilities();
			printTargetHypotheses();
		}

//		ArrayList<FactHypothesis> hyps1 = new ArrayList<>();
//		FactHypothesis h1 = new FactHypothesis("rain", 0.1);
//		hyps1.add(h1);
//		FactHypothesis h2 = new FactHypothesis("dry", 0.2);
//		hyps1.add(h2);
//		FactHypothesis h3 = new FactHypothesis("rain", 0.3);
//		hyps1.add(h3);
//		FactHypothesis h4 = new FactHypothesis("dry", 0.5);
//		hyps1.add(h4);
//		FactHypothesis h5 = new FactHypothesis("rain", 0.8);
//		hyps1.add(h5);
//		FactHypothesis h6 = new FactHypothesis("dry", 0.9);
//		hyps1.add(h6);
//
//		sortHypotheses(hyps1);
//		for (int i = 0; i < hyps1.size(); i++){
//
//			System.out.println("Hypothesis " + (i + 1) + ": " + targetFact.getName() + " is " +
//					hyps1.get(i).getName() + " : " + hyps1.get(i).getProbability());
//		}
		
		
		
	}

	// get hypotheses of target fact
	// print them in order (most likely first) with values
	private static void printTargetHypotheses() {

		ArrayList<FactHypothesis> hyps = targetFact.getHypotheses();
		hyps = sortHypotheses(hyps);

		System.out.println("\n=============================================");

		for (int i = 0; i < hyps.size(); i++){

			System.out.println("Hypothesis " + (i + 1) + ": " + targetFact.getName() + " is " +
					hyps.get(i).getName() + " : " + hyps.get(i).getProbability());
		}

		System.out.println("=============================================\n");

	}

	// sorts an array of hypotheses
	private static ArrayList<FactHypothesis> sortHypotheses(ArrayList<FactHypothesis> hyps){

		FactHypothesis tempHyp;

		for (int i = 0; i < hyps.size(); i++) {

			for (int j = (i + 1); j < hyps.size(); j++) {

				if (hyps.get(j).getProbability() > hyps.get(i).getProbability()){
					tempHyp = hyps.get(j);
					hyps.set(j, hyps.get(i));
					hyps.set(i, tempHyp);
				}
			}
		}

		return hyps;

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
				boolean noUnknownAntecedents = true;
				ArrayList<Fact> antecedents = rule.getAntecedents();
				for (int j = 0; j < antecedents.size() && noUnknownAntecedents; j++){
					
					String antName = antecedents.get(j).getName();
					int factTestIndex = findFactIndex(antName);
					
					// if fact is not known...
					if (!database.get(factTestIndex).isKnown()){
						
						noUnknownAntecedents = false;
					}
						
				}
				
			 	//if rule is unevaluated and rule has 0 unknown antecedents
				if (noUnknownAntecedents){
					
			 		//update the hypotheses of the target fact using LS and LN values of that rule
					
					String consVal = rule.getConsequent().getValue();

					//test
//					System.out.println("\ncons Name = " + consVal);

					double factor;
					double priorProbability = targetFact.getHypothesis(consVal).getProbability();
					
					
					// evaluate rule to determine whether to use LN or LS
					if (ruleCondsMet(rule))
						factor = rule.getLS();
					else
						factor = rule.getLN();
					
					
					targetFact.getHypothesis(consVal).setProbability(updateProbability(priorProbability, factor));

					rule.setEvaluated();
				}
			}
		}

		return true;
	}


	private static boolean ruleCondsMet(Rule rule){


		ArrayList<Fact> ants = rule.getAntecedents();
		boolean result = true;

		// if OR rule, only needs one antecedent to be true for the result to be true
		if (rule.getLogicType().equals("OR")) {
			result = false;

			for (int i = 0; i < ants.size() && !result; i++){

				String antValue = ants.get(i).getValue();
				String knownFactValue = database.get(findFactIndex(ants.get(i).getName())).getValue();

				if (antValue.equals(knownFactValue)){
					result = true;
				}
			}
		}

		// if AND rule, only needs one antecedent to be false for the result to be false
		else if (rule.getLogicType().equals("AND")){

			result = true;

			for (int i = 0; i < ants.size() && result; i++){

				String antValue = ants.get(i).getValue();
				String knownFactValue = database.get(findFactIndex(ants.get(i).getName())).getValue();

				if (!antValue.equals(knownFactValue)){
					result = false;
				}
			}
		}
		else {
			System.out.println("Error in evaluateByRule() - unknown logic type");
		}


		
		return result;
	}
	
	private static double updateProbability(double priorProbability, double factor){

//		System.out.println("Prior Probability = " + priorProbability + ", factor = " + factor);

		double preOdds = priorProbability / (1.0 - priorProbability);

		double postOdds = preOdds * factor;
		
		double postProbability = postOdds / (1.0 + postOdds);


//		System.out.println("Post probability = " + postProbability);

		return postProbability;
		
	}
	
	// build question text according to index - if index is out of range, return escape string
	// ask question
	// check answer is a valid answer - if not repeat question
	// return string
	private static String qAndA(int factDatabaseIndex){
		
		String qText;
		
		// check fact index is in range
		if (factDatabaseIndex >= database.size()
				|| factDatabaseIndex < 0) {
			return "Q";
		}


		// build question with permitted values
		ArrayList<String> possVals = database.get(factDatabaseIndex).getPossValues();
		qText = "What is the " + database.get(factDatabaseIndex).getName() + " today (";
		for (int i = 0; i < possVals.size(); i++){
			qText += possVals.get(i);
			if(i < possVals.size() - 1)
				qText += "/";
		}
		qText += "):";
		
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
