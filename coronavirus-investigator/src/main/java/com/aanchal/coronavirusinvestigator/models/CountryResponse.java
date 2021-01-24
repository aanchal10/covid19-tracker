package com.aanchal.coronavirusinvestigator.models;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryResponse {


		@JsonProperty("confirmed")
		private Confirmed confirmed;
		@JsonProperty("recovered")
		private Recovered recovered;
		@JsonProperty("deaths")
		private Deaths deaths;
		@JsonProperty("lastUpdate")
		private String lastUpdate;
		

		@JsonProperty("confirmed")
		public Confirmed getConfirmed() {
		return confirmed;
		}

		@JsonProperty("confirmed")
		public void setConfirmed(Confirmed confirmed) {
		this.confirmed = confirmed;
		}

		@JsonProperty("recovered")
		public Recovered getRecovered() {
		return recovered;
		}

		@JsonProperty("recovered")
		public void setRecovered(Recovered recovered) {
		this.recovered = recovered;
		}

		@JsonProperty("deaths")
		public Deaths getDeaths() {
		return deaths;
		}

		@JsonProperty("deaths")
		public void setDeaths(Deaths deaths) {
		this.deaths = deaths;
		}

		@JsonProperty("lastUpdate")
		public String getLastUpdate() {
		return lastUpdate;
		}

		@JsonProperty("lastUpdate")
		public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
		}

	}

