package entity;

import java.util.Map;

public class Record {
	private String userId;
	private Map<String, String> makers;
	
	// using builder pattern for potential info we need to determine our test result, our record class could be more complex
	public static class RecordBuilder {
		private String userId;
		private Map<String, String> makers;
		
		public RecordBuilder setUserId(String userId) {
			this.userId = userId;
			return this;
		}
		
		public RecordBuilder setMakers( Map<String, String> makers) {
			this.makers = makers;
			return this;
		}
		
		public Record build() {
			return new Record(this);
		}
	}
	
	private Record(RecordBuilder builder) {
		this.userId = builder.userId;
		this.makers = builder.makers;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public Map<String, String> getMarkers() {
		return makers;
	}
	
}
