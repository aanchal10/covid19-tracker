package com.aanchal.coronavirusinvestigator.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MainlandChina {

	@JsonProperty("reportDate")
	private Integer reportDate;
	@JsonProperty("mainlandChina")
	private Integer mainlandChina;
	@JsonProperty("otherLocations")
	private Integer otherLocations;
	@JsonProperty("totalConfirmed")
	private Integer totalConfirmed;
	@JsonProperty("totalRecovered")
	private Integer totalRecovered;
	@JsonProperty("reportDateString")
	private String reportDateString;
	@JsonProperty("deltaConfirmed")
	private Integer deltaConfirmed;
	@JsonProperty("deltaRecovered")
	private Integer deltaRecovered;
	@JsonProperty("objectid")
	private Integer objectid;
	
	@JsonProperty("reportDate")
	public Integer getReportDate() {
	return reportDate;
	}

	@JsonProperty("reportDate")
	public void setReportDate(Integer reportDate) {
	this.reportDate = reportDate;
	}

	@JsonProperty("mainlandChina")
	public Integer getMainlandChina() {
	return mainlandChina;
	}

	@JsonProperty("mainlandChina")
	public void setMainlandChina(Integer mainlandChina) {
	this.mainlandChina = mainlandChina;
	}

	@JsonProperty("otherLocations")
	public Integer getOtherLocations() {
	return otherLocations;
	}

	@JsonProperty("otherLocations")
	public void setOtherLocations(Integer otherLocations) {
	this.otherLocations = otherLocations;
	}

	@JsonProperty("totalConfirmed")
	public Integer getTotalConfirmed() {
	return totalConfirmed;
	}

	@JsonProperty("totalConfirmed")
	public void setTotalConfirmed(Integer totalConfirmed) {
	this.totalConfirmed = totalConfirmed;
	}

	@JsonProperty("totalRecovered")
	public Integer getTotalRecovered() {
	return totalRecovered;
	}

	@JsonProperty("totalRecovered")
	public void setTotalRecovered(Integer totalRecovered) {
	this.totalRecovered = totalRecovered;
	}

	@JsonProperty("reportDateString")
	public String getReportDateString() {
	return reportDateString;
	}

	@JsonProperty("reportDateString")
	public void setReportDateString(String reportDateString) {
	this.reportDateString = reportDateString;
	}

	@JsonProperty("deltaConfirmed")
	public Integer getDeltaConfirmed() {
	return deltaConfirmed;
	}

	@JsonProperty("deltaConfirmed")
	public void setDeltaConfirmed(Integer deltaConfirmed) {
	this.deltaConfirmed = deltaConfirmed;
	}

	@JsonProperty("deltaRecovered")
	public Integer getDeltaRecovered() {
	return deltaRecovered;
	}

	@JsonProperty("deltaRecovered")
	public void setDeltaRecovered(Integer deltaRecovered) {
	this.deltaRecovered = deltaRecovered;
	}

	@JsonProperty("objectid")
	public Integer getObjectid() {
	return objectid;
	}

	@JsonProperty("objectid")
	public void setObjectid(Integer objectid) {
	this.objectid = objectid;
	}


}
