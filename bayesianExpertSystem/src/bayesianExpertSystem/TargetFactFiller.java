package bayesianExpertSystem;

import java.util.ArrayList;
import java.util.Arrays;

public class TargetFactFiller {
	
	private static TargetFact tf;

	public TargetFactFiller() {
		fillTargetFact();
	}
	
	private static void fillTargetFact(){
		
		FactHypothesis h1 = new FactHypothesis("rain", 0.5);
		FactHypothesis h2 = new FactHypothesis("dry", 0.5);
		
		ArrayList<FactHypothesis> hyps = new ArrayList<FactHypothesis>(Arrays.asList(h1, h2));
		
		TargetFact newFact = new TargetFact("weather tomorrow", hyps);
		
		tf = newFact;
	}
	
	public TargetFact getTargetFact(){return tf;}

}
