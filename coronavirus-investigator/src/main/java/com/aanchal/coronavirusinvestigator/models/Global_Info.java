package com.aanchal.coronavirusinvestigator.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Global_Info {

	@JsonProperty("confirmed")
	private Confirmed confirmed;
	@JsonProperty("recovered")
	private Recovered recovered;
	@JsonProperty("deaths")
	private Deaths deaths;
	@JsonProperty("dailySummary")
	private String dailySummary;
	@JsonProperty("dailyTimeSeries")
	private DailyTimeSeries dailyTimeSeries;
	@JsonProperty("image")
	private String image;
	@JsonProperty("source")
	private String source;
	@JsonProperty("countries")
	private String countries;
	@JsonProperty("countryDetail")
	private CountryDetail countryDetail;
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

	@JsonProperty("dailySummary")
	public String getDailySummary() {
	return dailySummary;
	}

	@JsonProperty("dailySummary")
	public void setDailySummary(String dailySummary) {
	this.dailySummary = dailySummary;
	}

	@JsonProperty("dailyTimeSeries")
	public DailyTimeSeries getDailyTimeSeries() {
	return dailyTimeSeries;
	}

	@JsonProperty("dailyTimeSeries")
	public void setDailyTimeSeries(DailyTimeSeries dailyTimeSeries) {
	this.dailyTimeSeries = dailyTimeSeries;
	}

	@JsonProperty("image")
	public String getImage() {
	return image;
	}

	@JsonProperty("image")
	public void setImage(String image) {
	this.image = image;
	}

	@JsonProperty("source")
	public String getSource() {
	return source;
	}

	@JsonProperty("source")
	public void setSource(String source) {
	this.source = source;
	}

	@JsonProperty("countries")
	public String getCountries() {
	return countries;
	}

	@JsonProperty("countries")
	public void setCountries(String countries) {
	this.countries = countries;
	}

	@JsonProperty("countryDetail")
	public CountryDetail getCountryDetail() {
	return countryDetail;
	}

	@JsonProperty("countryDetail")
	public void setCountryDetail(CountryDetail countryDetail) {
	this.countryDetail = countryDetail;
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
