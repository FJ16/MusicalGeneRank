package algorithm;

import java.util.HashMap;
import java.util.Map;

import entity.Record;
import external.GeneInfoAPI;

public class MusicalRank {
	private static final String[] MUSICAL = {"rs4630083", "rs13146789", "rs4349633", "rs3803"};  // default maker id
	
	public static void main(String[] args) {
		MusicalRank t = new MusicalRank();
		String res = t.calculateScore("demo_profile_id");
		System.out.println(res);
	}
	
	public String calculateScore(String userID) {
		GeneInfoAPI geneInfo = new GeneInfoAPI();
		Record personalRecord = geneInfo.searchMusician(MUSICAL, userID);
		
		Map<String, Map<String, String>> standards = getStandards();
		
		double res = 0.0;
		
		// calculate the result
		for (Map.Entry<String, String> info : personalRecord.getMarkers().entrySet()) {
			String marker = info.getKey();
			String gene = info.getValue();
			if (standards.containsKey(marker)) {
				res += Double.valueOf(standards.get(marker).get(gene));
			}
		}
		
		return String.valueOf(res);
	}
	
	// generate fake subScores map
	private Map<String, Map<String, String>> getStandards() {
		Map<String, Map<String, String>> subScores = new HashMap<>();
		
		Map<String, String> subMap1 = new HashMap<>();
		subMap1.put("GG", "2.24");
		subMap1.put("AG","1.32");
		subMap1.put("GA","1.32");
		subMap1.put("AA","0");
		subScores.put("rs4630083", subMap1);
		
		Map<String, String> subMap2 = new HashMap<>();
		subMap2.put("TT", "1.0");
		subMap2.put("TG","0.82");
		subMap2.put("GT","0.82");
		subMap2.put("GG","0");
		subScores.put("rs13146789", subMap2);
		
		Map<String, String> subMap3 = new HashMap<>();
		subMap3.put("AA", "1.11");
		subMap3.put("AG","0");
		subMap3.put("GA","0");
		subMap3.put("GG","0");
		subScores.put("rs4349633", subMap3);
		
		Map<String, String> subMap4 = new HashMap<>();
		subMap4.put("GG", "0");
		subMap4.put("AG","0");
		subMap4.put("GA","0");
		subMap4.put("AA","0.65");
		subScores.put("rs3803", subMap4);
		
		return subScores;
	}
	
}
