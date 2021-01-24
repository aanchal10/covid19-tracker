package com.aanchal.coronavirusinvestigator.controllers;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aanchal.coronavirusinvestigator.models.Confirmed;
import com.aanchal.coronavirusinvestigator.models.CountryResponse;
import com.aanchal.coronavirusinvestigator.models.Data;
import com.aanchal.coronavirusinvestigator.models.Global_Info;
import com.aanchal.coronavirusinvestigator.models.IndianStatesInfo;
import com.aanchal.coronavirusinvestigator.models.LocationInfo;
import com.aanchal.coronavirusinvestigator.models.MainlandChina;
import com.aanchal.coronavirusinvestigator.models.Regional;
import com.aanchal.coronavirusinvestigator.models.Summary;
import com.aanchal.coronavirusinvestigator.service.CoronavirusDataService;
import com.aanchal.coronavirusinvestigator.util.HitManager;
import com.google.gson.Gson;


@Controller
public class HomeController {
	
	@Autowired
	CoronavirusDataService coronavirusDataService;
	
	@Value("${covidplatform.virus_total_global_data}")
	String mathdrdo_global_url;
	
	@Value("${covidplatform.virus_total_India_data}")
	String mathdrdo_india_url;
	
	@Value("${covidplatform.virus_total_global_all_info_data}")
	String mathdrdo_global_confirmed_url;
	
	@Value("${covidplatform.virus_total_India_data}")
	String virus_total_India_data;
	
	@Value("${covidplatform.mainland_china}")
	String mainland_china;
	
	@Value("${covidplatform.indian_states}")
	String indian_states;
	
	Confirmed confirmed;
	Global_Info gi;
	CountryResponse countryResponse;
	MainlandChina mainlandChinaResponse;
	IndianStatesInfo indianStatesInfo;
	Summary summary;
	Map<String, Integer> india = new LinkedHashMap<>();
	
	float most_affected_percent;
	float mainland_china_percent;
	float others;
	float india_conf_percent;
	String high_affected_country;
	
	long most_affected_conf = 0;
	long most_affected_recovered = 0;
	long most_affected_deaths = 0;
	int highestDiff = 0;
	String global_last_update = "";
	String indian_last_update ="";
	int highest_indian_state = 600;
	

	public Map<String, Integer> getIndia() {
		return india;
	}

	public void setIndia(Map<String, Integer> india) {
		this.india = india;
	}

	@GetMapping("/")
	public String test(Model model) {
		
		final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
		
		int hits = HitManager.getHits();
		HitManager.setHits(hits + 1);
		System.out.println("No of hits are "+HitManager.getHits());
		LOGGER.info("Love and support from " +HitManager.getHits() + "people");
		int hits_now = HitManager.getHits();
		
		List<LocationInfo> information = coronavirusDataService.getAllInfo();
//		int totalDeaths = coronavirusDataService.getTotalDeaths();
//		int totalRecovered = coronavirusDataService.getTotalRecovered();
		int highestDiff = coronavirusDataService.getHighestDiff();
		String city = coronavirusDataService.getCountry();
		
		
		String global_response = coronavirusDataService.fetchAPIDetails(mathdrdo_global_url);
		
		//Fetching entire global data
		try {
 
			gi = new Gson().fromJson(global_response, Global_Info.class);
			System.out.println(gi);
			confirmed = gi.getConfirmed();
			int conf_cases = confirmed.getValue();
			global_last_update = gi.getLastUpdate().replace("T", "   ").replace(".000Z","  ");
            System.out.println(conf_cases);
            LOGGER.info("Visitor no: {} - Global confirmed cases: {}", hits_now, conf_cases);
	    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		int totalReportedCases = information.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = information.stream().mapToInt(stat -> stat.getDiffFromPreviousDay()).sum();
//		int totalSick = totalReportedCases - totalDeaths - totalRecovered;
		
		int totalReportedCases = gi.getConfirmed().getValue();
		long totalDeaths = gi.getDeaths().getValue();
		long totalRecovered = gi.getRecovered().getValue();
		int totalSick = (int) (totalReportedCases - totalRecovered - totalDeaths);
		
		//float deathPercent = (totalDeaths*100)/totalReportedCases;
		
		String lastUpdate = gi.getLastUpdate();
		
		
		//Fetching data of most affected country
		String most_affected_country_url = gi.getCountries()+"/"+city;
		String most_affected_country_response = coronavirusDataService.fetchAPIDetails(most_affected_country_url);
		
		if(most_affected_country_response.equals("error"))
		{
			city = "Unavailable at Present";
			highestDiff = 0;
		}
		else {	
		try {
					 
			countryResponse = new Gson().fromJson(most_affected_country_response, CountryResponse.class);
			System.out.println(countryResponse);
			int most_affected_country_confirmed_cases = countryResponse.getConfirmed().getValue();
	        System.out.println(most_affected_country_confirmed_cases);
	        LOGGER.info("Visitor no: {} - Most affected country confirmed casses : {} ",hits_now, most_affected_country_confirmed_cases);
			    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		most_affected_conf = countryResponse.getConfirmed().getValue();
	    most_affected_recovered = countryResponse.getRecovered().getValue();
		most_affected_deaths = countryResponse.getDeaths().getValue();
		}
		
		//Fetching data of India
	
//		String india_response = coronavirusDataService.fetchAPIDetails(virus_total_India_data);
//				
//		try {
//					 
//			countryResponse = new Gson().fromJson(india_response, CountryResponse.class);
//			System.out.println(countryResponse);
//			int india_confirmed_cases = countryResponse.getConfirmed().getValue();
//	        System.out.println(india_confirmed_cases);
//	        LOGGER.info("Visitor no: {} - India confirmed casses : {} ",hits_now, india_confirmed_cases);
//			    
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//			
//		int india_conf = countryResponse.getConfirmed().getValue();
//		int india_recovered = countryResponse.getRecovered().getValue();
//		int india_deaths = countryResponse.getDeaths().getValue();
//		
//	   int single_day_India_increase = 0;	
//       for(LocationInfo a : information) {
//			
//			if(a.getCountry().equalsIgnoreCase("India")) {
//				a.setLatestTotalCases(india_conf);
//				single_day_India_increase = a.getDiffFromPreviousDay();
//				break;
//			}
//		}
       
     //Fetching data of MainLand China
       
       int china_confirmed_cases=0;
       int delta_new_cases = 0;
   	
   		String mainland_china_response = coronavirusDataService.fetchAPIDetails(mainland_china);
   		System.out.println(mainland_china_response);
   		String china_response = coronavirusDataService.fetchAPIDetails(gi.getCountries()+"/"+"China");	
   				
   		try {
   			
   			JSONArray jsonarray = new JSONArray(mainland_china_response);
   			
   			JSONObject jsonobject = jsonarray.getJSONObject(jsonarray.length()-1);
   			delta_new_cases = jsonobject.getInt("deltaConfirmed");
   			
   	        LOGGER.info("Visitor no: {} - Delta cases : {} ",hits_now, delta_new_cases);
   	        
   	        countryResponse = new Gson().fromJson(china_response, CountryResponse.class);
  	        System.out.println(countryResponse);
  	        china_confirmed_cases = countryResponse.getConfirmed().getValue();
            System.out.println(china_confirmed_cases);
         
         
           LOGGER.info("Visitor no: {} - Mainland China confirmed casses : {} ",hits_now, china_confirmed_cases);
   			    
   		} catch (Exception e) {
   			e.printStackTrace();
   		}
       
       
       int china_affected_conf = countryResponse.getConfirmed().getValue();
	   int china_affected_recovered = countryResponse.getRecovered().getValue();
	   int china_affected_deaths = countryResponse.getDeaths().getValue();
	   
	   
	 //Fetching data of Indian States
	   
	   List<Regional> city_info = new ArrayList<>(); 
	   String indian_states_response = coronavirusDataService.fetchAPIDetails(indian_states);
	   Map<String, Integer> india = new LinkedHashMap<>();
		
		try {
					 
			indianStatesInfo = new Gson().fromJson(indian_states_response, IndianStatesInfo.class);
			indian_last_update = indianStatesInfo.getLastOriginUpdate().replace("T", "   ").replace(".000Z","  ");
			System.out.println(indianStatesInfo);
			
			summary = indianStatesInfo.getData().getSummary();		
			
			JSONArray jsonarray = new JSONArray(indianStatesInfo.getData().getRegional());
			
			
			for(int i = 0; i < jsonarray.length(); i++) {
		    
			JSONObject jsonobject = jsonarray.getJSONObject(i);		
			Regional value = new Regional();
			value.setLoc(jsonobject.getString("loc"));
			value.setConfirmedCasesIndian(jsonobject.getInt("confirmedCasesIndian")); 
			int local_indian = jsonobject.getInt("confirmedCasesIndian");
			value.setConfirmedCasesForeign(jsonobject.getInt("confirmedCasesForeign"));
			int foreign_indian = jsonobject.getInt("confirmedCasesForeign");
			value.setDeaths(jsonobject.getInt("deaths"));
			value.setTotalIndian(local_indian + foreign_indian);
			city_info.add(value);
			india.put(jsonobject.getString("loc"),local_indian + foreign_indian);
			
				
			}
	       this.india = india;
			    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int india_conf = summary.getTotal();
		int india_indian_nationals = summary.getConfirmedCasesIndian();
		int india_foreign_nationals = summary.getConfirmedCasesForeign();
		long india_recovered = summary.getDischarged();
		long india_deaths = summary.getDeaths();
		
	   int single_day_India_increase = 0;	
       for(LocationInfo a : information) {
			
			if(a.getCountry().equalsIgnoreCase("India")) {
				a.setLatestTotalCases(india_conf);
				single_day_India_increase = a.getDiffFromPreviousDay();
				break;
			}
		}
		
		
		 Collections.sort(city_info, new Comparator<Regional>() {
	            public int compare(Regional s1, Regional s2) {
	                // notice the cast to (Integer) to invoke compareTo
	                return ((Integer)s2.getTotalIndian()).compareTo(s1.getTotalIndian());
	            }
	        });
		 
		 Regional ri = city_info.get(0);
		 highest_indian_state = ri.getTotalIndian();
		 
		//Pie-Chart Data 
		this.most_affected_percent = (most_affected_conf * 100)/totalReportedCases;
		this.high_affected_country = city;
		this.mainland_china_percent = (china_affected_conf * 100)/totalReportedCases;
		this.india_conf_percent = (india_conf * 100)/totalReportedCases;
		this.others = 100.0F - this.most_affected_percent - this.mainland_china_percent-this.india_conf_percent;
		
		System.out.println(" Most affected cpuntry "+ this.most_affected_percent);
		System.out.println(" Mainland China "+ this.mainland_china_percent);
		System.out.println(" Others "+ this.others);
		
		HashMap<String,Integer> inform = new HashMap<>();
		for(LocationInfo info : information) {
			inform.put(info.getCountry(),info.getLatestTotalCases());
		}
		
        float deathPercent = (totalDeaths*100)/totalReportedCases;
		LOGGER.info("Death percent {}",deathPercent);
		
        float recoveryPercent = (totalRecovered*100)/totalReportedCases;
		LOGGER.info("Recovery percent {}",recoveryPercent);
		
		float indiaDeathPercent = (india_deaths*100)/india_conf;
		LOGGER.info("India Death percent {}",indiaDeathPercent);
		

        float indiaRecoveryPercent = (india_recovered*100)/india_conf;
		LOGGER.info("India Recovery percent {}",indiaRecoveryPercent);
		
		float mostAffectedDeathPercent = (most_affected_deaths*100)/most_affected_conf;
		LOGGER.info("Most affected Death percent {}",mostAffectedDeathPercent);
		

        float mostAffectedRecoveryPercent = (most_affected_recovered*100)/most_affected_conf;
		LOGGER.info("Most affected percent {}",mostAffectedRecoveryPercent);
		
		
		
		model.addAttribute("locationInfo",coronavirusDataService.getAllInfo());
		model.addAttribute("latestTotalCases",NumberFormat.getNumberInstance(Locale.US).format(totalReportedCases));
		model.addAttribute("latestNewCases",NumberFormat.getNumberInstance(Locale.US).format(totalNewCases));
		model.addAttribute("totalDeaths",NumberFormat.getNumberInstance(Locale.US).format(totalDeaths));
		model.addAttribute("totalRecovered",NumberFormat.getNumberInstance(Locale.US).format(totalRecovered));
		model.addAttribute("totalSick",NumberFormat.getNumberInstance(Locale.US).format(totalSick));
		model.addAttribute("highestDiff",NumberFormat.getNumberInstance(Locale.US).format(highestDiff));
		model.addAttribute("city",city);
		model.addAttribute("deathPercent",deathPercent +" %");
		model.addAttribute("recoveryPercent",recoveryPercent +" %");
		model.addAttribute("most_affected_conf",NumberFormat.getNumberInstance(Locale.US).format(most_affected_conf));
		model.addAttribute("most_affected_recovered",NumberFormat.getNumberInstance(Locale.US).format(most_affected_recovered));
		model.addAttribute("most_affected_deaths",NumberFormat.getNumberInstance(Locale.US).format(most_affected_deaths));
		model.addAttribute("mostAffectedRecoveryPercent",NumberFormat.getNumberInstance(Locale.US).format(mostAffectedRecoveryPercent)+" %");
		model.addAttribute("mostAffectedDeathPercent",NumberFormat.getNumberInstance(Locale.US).format(mostAffectedDeathPercent)+" %");
		model.addAttribute("mostAffectedActive",NumberFormat.getNumberInstance(Locale.US).format(most_affected_conf - most_affected_recovered - most_affected_deaths ));
		
		model.addAttribute("india_conf",NumberFormat.getNumberInstance(Locale.US).format(india_conf));
		model.addAttribute("india_recovered",NumberFormat.getNumberInstance(Locale.US).format(india_recovered));
		model.addAttribute("india_deaths",NumberFormat.getNumberInstance(Locale.US).format(india_deaths));
		model.addAttribute("single_day_India_increase",NumberFormat.getNumberInstance(Locale.US).format(single_day_India_increase));
		model.addAttribute("indiaRecoveryPercent",NumberFormat.getNumberInstance(Locale.US).format(indiaRecoveryPercent)+" %");
		model.addAttribute("indiaDeathPercent",NumberFormat.getNumberInstance(Locale.US).format(indiaDeathPercent)+" %");
		model.addAttribute("indiaAffectedActive",NumberFormat.getNumberInstance(Locale.US).format(india_conf - india_recovered - india_deaths));
		
		model.addAttribute("china_affected_conf",NumberFormat.getNumberInstance(Locale.US).format(china_affected_conf));
		model.addAttribute("china_affected_recovered",NumberFormat.getNumberInstance(Locale.US).format(china_affected_recovered));
		model.addAttribute("china_affected_deaths",NumberFormat.getNumberInstance(Locale.US).format(china_affected_deaths));
		model.addAttribute("lastUpdate",lastUpdate);
		model.addAttribute("city_info",city_info);
		model.addAttribute("india_indian_nationals",NumberFormat.getNumberInstance(Locale.US).format(india_indian_nationals));
		model.addAttribute("india_foreign_nationals",NumberFormat.getNumberInstance(Locale.US).format(india_foreign_nationals));
		model.addAttribute("india",india);
		model.addAttribute("indian_last_update",indian_last_update);
		model.addAttribute("highest_indian_state",highest_indian_state);
		model.addAttribute("inform",inform);
		
		System.out.println("Hey, I am hit");
		
		
		
		return "home";
	}
	
	@GetMapping("/displayBarGraph")
	public String barGraph(Model model) {
		Map<String, Integer> IndiaMap = this.getIndia();
		
		model.addAttribute("IndiaMap", IndiaMap);
		model.addAttribute("highest_indian_state",highest_indian_state+100);
		return "barGraph";
	}
	
	
	@GetMapping("/displayPieChart")
	public String pieChart(Model model) {
		model.addAttribute("highest",  Math.round(this.most_affected_percent));
		model.addAttribute("china", Math.round(this.mainland_china_percent));
		model.addAttribute("others",  Math.round(this.others));
		return "pieChart";
	}
	
	@GetMapping("/importantLinks")
	public String importantLinks() {
		
		return "importantLinks";
	}
	
	@GetMapping("/hello")
	public String hello(@RequestParam(name="name", required=false, defaultValue="World") String name,
			@RequestParam(name="city", required=false, defaultValue="Mumbai") String city, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("city", city );
		return "allInfo";
	}
}
