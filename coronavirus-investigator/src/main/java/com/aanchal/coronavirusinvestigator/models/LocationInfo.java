package com.aanchal.coronavirusinvestigator.models;

public class LocationInfo {
	
		private String state;
		private String country;
		private int latestTotalCases;
		private int diffFromPreviousDay;
		
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public int getLatestTotalCases() {
			return latestTotalCases;
		}
		public void setLatestTotalCases(int latestTotalCases) {
			this.latestTotalCases = latestTotalCases;
		}
		
		public int getDiffFromPreviousDay() {
			return diffFromPreviousDay;
		}
		public void setDiffFromPreviousDay(int diffFromPreviousDay) {
			this.diffFromPreviousDay = diffFromPreviousDay;
		}
		
		@Override
		public String toString() {
			return "LocationInfo [state=" + state + ", country=" + country + ", latestTotalCases=" + latestTotalCases
					+ "]";
		}
		
		
	

}
