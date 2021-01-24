package com.aanchal.coronavirusinvestigator.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IndianStatesInfo {	

@JsonProperty("success")
private Boolean success;
@JsonProperty("data")
private Data data;
@JsonProperty("lastRefreshed")
private String lastRefreshed;
@JsonProperty("lastOriginUpdate")
private String lastOriginUpdate;

@JsonProperty("success")
public Boolean getSuccess() {
return success;
}

@JsonProperty("success")
public void setSuccess(Boolean success) {
this.success = success;
}

@JsonProperty("data")
public Data getData() {
return data;
}

@JsonProperty("data")
public void setData(Data data) {
this.data = data;
}

@JsonProperty("lastRefreshed")
public String getLastRefreshed() {
return lastRefreshed;
}

@JsonProperty("lastRefreshed")
public void setLastRefreshed(String lastRefreshed) {
this.lastRefreshed = lastRefreshed;
}

@JsonProperty("lastOriginUpdate")
public String getLastOriginUpdate() {
return lastOriginUpdate;
}

@JsonProperty("lastOriginUpdate")
public void setLastOriginUpdate(String lastOriginUpdate) {
this.lastOriginUpdate = lastOriginUpdate;
}

}
