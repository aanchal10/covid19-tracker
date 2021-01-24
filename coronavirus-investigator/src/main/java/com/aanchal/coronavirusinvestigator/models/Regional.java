package com.aanchal.coronavirusinvestigator.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Regional {

	@JsonProperty("loc")
	private String loc;
	@JsonProperty("confirmedCasesIndian")
	private Integer confirmedCasesIndian;
	@JsonProperty("confirmedCasesForeign")
	private Integer confirmedCasesForeign;
	@JsonProperty("discharged")
	private Integer discharged;
	@JsonProperty("deaths")
	private Integer deaths;
	@JsonProperty("totalIndian")
	private Integer totalIndian;
	
	@JsonProperty("loc")
	public String getLoc() {
	return loc;
	}

	@JsonProperty("loc")
	public void setLoc(String loc) {
	this.loc = loc;
	}

	@JsonProperty("confirmedCasesIndian")
	public Integer getConfirmedCasesIndian() {
	return confirmedCasesIndian;
	}

	@JsonProperty("confirmedCasesIndian")
	public void setConfirmedCasesIndian(Integer confirmedCasesIndian) {
	this.confirmedCasesIndian = confirmedCasesIndian;
	}

	@JsonProperty("confirmedCasesForeign")
	public Integer getConfirmedCasesForeign() {
	return confirmedCasesForeign;
	}

	@JsonProperty("confirmedCasesForeign")
	public void setConfirmedCasesForeign(Integer confirmedCasesForeign) {
	this.confirmedCasesForeign = confirmedCasesForeign;
	}

	@JsonProperty("discharged")
	public Integer getDischarged() {
	return discharged;
	}

	@JsonProperty("discharged")
	public void setDischarged(Integer discharged) {
	this.discharged = discharged;
	}

	@JsonProperty("deaths")
	public Integer getDeaths() {
	return deaths;
	}

	@JsonProperty("deaths")
	public void setDeaths(Integer deaths) {
	this.deaths = deaths;
	}
	

	public Integer getTotalIndian() {
		return totalIndian;
	}

	public void setTotalIndian(Integer totalIndian) {
		this.totalIndian = totalIndian;
	}

}
