package com.aanchal.coronavirusinvestigator.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {

	@JsonProperty("summary")
	private Summary summary;
	@JsonProperty("regional")
	private List<Regional> regional = null;
	
	@JsonProperty("summary")
	public Summary getSummary() {
	return summary;
	}

	@JsonProperty("summary")
	public void setSummary(Summary summary) {
	this.summary = summary;
	}

	@JsonProperty("regional")
	public List<Regional> getRegional() {
	return regional;
	}

	@JsonProperty("regional")
	public void setRegional(List<Regional> regional) {
	this.regional = regional;
	}
}
