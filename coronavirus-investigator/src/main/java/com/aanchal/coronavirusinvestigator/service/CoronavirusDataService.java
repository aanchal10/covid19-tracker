package com.aanchal.coronavirusinvestigator.service;



import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.aanchal.coronavirusinvestigator.models.Global_Info;
import com.aanchal.coronavirusinvestigator.models.LocationInfo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class CoronavirusDataService {

	@Value("${covidplatform.virus_data_john_url}")
	String john_hopkins_url;
	
	@Value("${covidplatform.virus_data_death_john__url}")
	String john_hopkins__death_url;
	
	@Value("${covidplatform.virus_data_recoverd_john_url}")
	String john_hopkins__recovered_url;
	
	private List<LocationInfo> allInfo = new ArrayList<>();	
	static OkHttpClient client = new OkHttpClient();
	private int totalDeaths;
	private int totalRecovered;
	private int highestDiff;
	private String country;
	

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<LocationInfo> getAllInfo() {
		return allInfo;
	}

	public void setAllInfo(List<LocationInfo> allInfo) {
		this.allInfo = allInfo;
	}
	
	public int getTotalDeaths() {
		return totalDeaths;
	}

	public void setTotalDeaths(int totalDeaths) {
		this.totalDeaths = totalDeaths;
	}

	public int getTotalRecovered() {
		return totalRecovered;
	}

	public void setTotalRecovered(int totalRecovered) {
		this.totalRecovered = totalRecovered;
	}
	
	 public int getHighestDiff() {
			return highestDiff;
	}

	public void setHighestDiff(int highestDiff) {
			this.highestDiff = highestDiff;
	}


	@PostConstruct
	//@Scheduled(cron = "0 * * * * *")
	public void fetchVirusData(){	
    try {
    	
        List<LocationInfo> newInfo = new ArrayList<>();
		int total_deaths = 0;
		int total_recovered = 0;
		int highestDiff = 0;
		String country = "";
        int LatestCases = 0;
        int previousDayCases = 0;
		
		Iterable<CSVRecord> records_confirmed = callAPI(john_hopkins_url, client);
//		Iterable<CSVRecord> records_death = callAPI(john_hopkins__death_url, client);
//		Iterable<CSVRecord> records_recovered = callAPI(john_hopkins__recovered_url, client);
			
		for (CSVRecord record : records_confirmed) {
		
			LocationInfo info = new LocationInfo();
			if(StringUtils.isEmpty(record.get("Province/State")))
				info.setState("Unknown");	
			else
				info.setState(record.get("Province/State"));
			
			info.setCountry(record.get("Country/Region"));
			if(StringUtils.isEmpty(record.get(record.size()-1)))
				LatestCases = 0;
			else
				LatestCases = Integer.parseInt(record.get(record.size()-1));
		
			if(StringUtils.isEmpty(record.size()-2))
                previousDayCases = 0;
            else
            	previousDayCases = Integer.parseInt(record.get(record.size()-2));

			info.setLatestTotalCases(LatestCases);
			if(LatestCases - previousDayCases > 0)
			    info.setDiffFromPreviousDay(LatestCases - previousDayCases);
			else
				info.setDiffFromPreviousDay(0);
			if((LatestCases - previousDayCases) > highestDiff)
			{
				 highestDiff = LatestCases - previousDayCases;
				 country =info.getCountry(); 
			}
			
                 		    
		    //System.out.println(info);
		    newInfo.add(info);	
		}
		
		

//		for (CSVRecord record : records_death) {
//		
//			total_deaths = total_deaths + Integer.parseInt(record.get(record.size()-1));	
//		}
//	
//		for (CSVRecord record : records_recovered) {
//			
//			total_recovered = total_recovered + Integer.parseInt(record.get(record.size()-1));	
//		}
//		
		 Collections.sort(newInfo, new Comparator<LocationInfo>() {
	            public int compare(LocationInfo s1, LocationInfo s2) {
	                // notice the cast to (Integer) to invoke compareTo
	                return ((Integer)s2.getLatestTotalCases()).compareTo(s1.getLatestTotalCases());
	            }
	        });
		 
		this.allInfo = newInfo;
		this.totalDeaths = total_deaths;
		this.totalRecovered = total_recovered;
		this.country = country;
		this.highestDiff = highestDiff;
		
		} catch (Exception e) {

			e.printStackTrace();
		}	
		
	}
	
   

	public static Iterable<CSVRecord> callAPI(String URL, OkHttpClient client) {
    	 
    	 Iterable<CSVRecord> records = null;
    	 try { 
    	  
    		 Request request = new Request.Builder()
    	            .url(URL)
    	            .get()
    	            .build();

    	     Response response;
				
				response = client.newCall(request).execute();
				
    	        String respStr = new String(response.body().bytes());
    	       // System.out.println(respStr);	
    	        StringReader in = new StringReader(respStr);
    			records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
                
				} catch (IOException e) {
					
					e.printStackTrace();
				}
    			 
    	 
		return records;
    	  
    	  
    	  
      }
	
	public String fetchAPIDetails(String URL) {
		
	Response response = null;	
		
	String respStr = " " ;
	try {	
		if (client == null)
		{
			OkHttpClient client = new OkHttpClient();
		}
		
		Request request = new Request.Builder()
	            .url(URL)
	            .get()
	            .build();

	     
		 response = client.newCall(request).execute();
		 
		 if(response.isSuccessful()) {
			 System.out.println("Success for that City");
		 }
		 else {
			 System.out.println("Success for that City");
			 return "error";
		 }
		 
		 respStr = new String(response.body().bytes());
	     System.out.println(respStr);
		
	}
	catch (Exception e){
		e.printStackTrace();	
	}
	return  respStr;
  }

	
	
}
