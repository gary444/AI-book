package bayesianExpertSystem;

import java.util.ArrayList;

public class RuleFiller {
	
	private static ArrayList<Rule> knowledgeBase = new ArrayList<Rule>();

	public RuleFiller(){
		populateKnowledgeBase();
		
		
	}
	
	/*
	
	private static void testRules() {
		
		for(int j = 0; j < knowledgeBase.size(); j++) {
			
			printRule(j);
		}
	}
	
	private static void printRule(int index) {
		
		Rule testRule;
		ArrayList<Fact> ants;
		Fact con;
		String logicType;
		
		testRule = knowledgeBase.get(index);
		
		ants = testRule.getAntecedents();
		con = testRule.getConsequent();
		logicType = testRule.getLogicType();
		
		// print antecedents
		
		System.out.println("Antecedents:\n");
		
		for (int i = 0; i < ants.size(); i++){
			
			System.out.print("Antecedent " + (i + 1) + ": ");
			System.out.print("IF " + ants.get(i).getName());
			
			if (ants.get(i).isRelationshipPositive())
				System.out.print(" IS ");
			else
				System.out.print(" IS NOT ");
			
			System.out.print(ants.get(i).getValue());
			
			if (i < (ants.size() - 1))
				System.out.print(" " + logicType);
			
			System.out.println("");
		}
		
		// print consequent
		
		System.out.print("\nConsequent:\n\nTHEN " + con.getName());
		
		if (con.isRelationshipPositive())
			System.out.print(" IS ");
		else
			System.out.print(" IS NOT ");
		
		System.out.print(con.getValue() + "\n\n");
		
	}
	
	*/
	
	// create a list of rules, all with antecedents, consequents and logic types
	private static void populateKnowledgeBase(){
		
		Rule newR;
		
		
		// RULE 1
		ArrayList<Fact> antecedents = new ArrayList<Fact>();
		Fact cf = new Fact("weather today", "rain");
		Fact consequent = new Fact("weather tomorrow", "rain");
		newR = new Rule(antecedents, consequent, "OR", 2.5, 0.6);
		knowledgeBase.add(newR);
		
		// RULE 2
		antecedents = new ArrayList<Fact>();
		cf = new Fact("weather today", "dry");
		antecedents.add(cf);
		consequent = new Fact("weather tomorrow", "dry");
		newR = new Rule(antecedents, consequent, "OR", 1.6, 0.4);
		knowledgeBase.add(newR);
		
		// RULE 3
		antecedents = new ArrayList<Fact>();
		cf = new Fact("weather today", "rain");
		antecedents.add(cf);
		cf = new Fact("rainfall", "low");
		antecedents.add(cf);
		consequent = new Fact("weather tomorrow", "dry");
		newR = new Rule(antecedents, consequent, "AND", 10.0, 1.0);
		knowledgeBase.add(newR);
		
		// RULE 4
		antecedents = new ArrayList<Fact>();
		cf = new Fact("weather today", "rain");
		antecedents.add(cf);
		cf = new Fact("rainfall", "low");
		antecedents.add(cf);
		cf = new Fact("temperature", "cold");
		antecedents.add(cf);
		consequent = new Fact("weather tomorrow", "dry");
		newR = new Rule(antecedents, consequent, "AND", 1.5, 1.0);
		knowledgeBase.add(newR);
		
		// RULE 5
		antecedents = new ArrayList<Fact>();
		cf = new Fact("weather today", "dry");
		antecedents.add(cf);
		cf = new Fact("temperature", "warm");
		antecedents.add(cf);
		consequent = new Fact("weather tomorrow", "rain");
		newR = new Rule(antecedents, consequent, "AND", 2.0, 0.9);
		knowledgeBase.add(newR);
		
		// RULE 6
		antecedents = new ArrayList<Fact>();
		cf = new Fact("weather today", "dry");
		antecedents.add(cf);
		cf = new Fact("temperature", "warm");
		antecedents.add(cf);
		cf = new Fact("cloud cover", "overcast");
		antecedents.add(cf);
		consequent = new Fact("weather tomorrow", "rain");
		newR = new Rule(antecedents, consequent, "AND", 5.0, 1.0);
		knowledgeBase.add(newR);
		
		
	}
	
	public ArrayList<Rule> getKnowledgeBase(){
		return knowledgeBase;
	}
}
