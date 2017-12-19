package external;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.Record;
import entity.Record.RecordBuilder;

public class GeneInfoAPI {
	private static final String API_HOST = "api.23andme.com";
	private static final String[] DEFAULT_TERM = {"rs4630083", "rs13146789", "rs4349633", "rs3803"};  // default maker id
	private static final String DEMO_TOKEN = "Bearer demo_oauth_token";
	
	public Record searchMusician(String[] query, String userID) { 
		String search_path = String.format("/3/profile/%s/marker/", userID);
		String url = "https://" + API_HOST + search_path;
		if (query == null) {
			query = DEFAULT_TERM;
		}
		
		try {
			
			//prepare the record builder
			RecordBuilder record = new RecordBuilder();
			record.setUserId(userID);

			Map<String, String> markers = new HashMap<>();
			for (String markerId : query) {
				markers.put(markerId, queryMarker(markerId, url));
			}
			
			record.setMakers(markers);
			return record.build();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	private String queryMarker(String markerId, String url) {
		try {
			HttpsURLConnection connection = (HttpsURLConnection) new URL(url + markerId).openConnection();
	
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", DEMO_TOKEN);
	
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			//get the response json array
			JSONObject responseJson = new JSONObject(response.toString());
			//parse the needed variables: allele type and its dosage
		    return genotypesHelper(responseJson);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String genotypesHelper(JSONObject res) {
		try {
			JSONArray variants = res.getJSONArray("variants");
			
			StringBuilder ret = new StringBuilder();
			for (int i = 0; i < variants.length(); i++) {
				JSONObject variant = variants.getJSONObject(i);
				int dosage = getIntFieldOrNull(variant, "dosage");
				String type = getStringFieldOrNull(variant, "allele");

				for (int d = 0; d < dosage; d++) 
					ret.append(type);
			}

			return ret.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String getStringFieldOrNull(JSONObject variant, String field) throws JSONException {
		return variant.isNull(field) ? null : variant.getString(field);
	}
	
	private Integer getIntFieldOrNull(JSONObject variant, String field) throws JSONException {
		return variant.isNull(field) ? null : variant.getInt(field);
	}
}
