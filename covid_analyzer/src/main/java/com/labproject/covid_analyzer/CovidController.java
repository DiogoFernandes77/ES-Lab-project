package com.labproject.covid_analyzer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.scheduling.annotation.Scheduled;
@Controller
public class CovidController {

	@Autowired
	private WorldRepository worldRepository;
	private static final Logger log = LoggerFactory.getLogger(CovidController.class); // A logger, to send output to the log
	private Random gen = new Random();
	
	
	@RequestMapping("/")
	public String root() {
		return "Greetings from Spring Boot!";
	}


	@PostMapping("/index")
    public String showPage(@RequestParam("selCountry") String country,RedirectAttributes redirectAttributes) {

        log.info(country);
		int confirmedCountry=0;
		int deathsCountry=0;
		int recoveredCountry=0;
		int activeCountry = 0;
		double avg_cases= 0;
		double avg_deaths= 0;
		double avg_recovered= 0;
		double avg_active= 0;
		ArrayList<CountryDay> ls = new ArrayList<>(7);
		//basicamente é ir buscar agr os stats para o pais em questao
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.covid19api.com/total/country/" + country;

		try{
			String json = restTemplate.getForObject(url, String.class);
			//log.info(json);
			//log.info("2");
			CountryEntry[] list = new ObjectMapper().readValue(json, CountryEntry[].class) ;
			//log.info("1");
			log.info(list[list.length-1].toString());
			confirmedCountry = Integer.parseInt(list[list.length-1].getConfirmed());
			deathsCountry = Integer.parseInt(list[list.length-1].getDeaths());
			recoveredCountry = Integer.parseInt(list[list.length-1].getRecovered());
			activeCountry = Integer.parseInt(list[list.length-1].getActive());
			//Date day = new SimpleDateFormat("dd-MM-yyyy").parse(list[list.length-1].getDate());
	
			int c= 0;
			int confirmedCountryWeek=0;
			int deathsCountryWeek=0;
			int recoveredCountryWeek=0;
			int activeCountryWeek = 0;

			
			for(int i = 1; i < 9; i++){
				CountryEntry day = list[list.length-i];
				CountryEntry dayBefore = list[(list.length-i)-1];
				Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(day.getDate());
			
				confirmedCountryWeek = Integer.parseInt(day.getConfirmed()) -Integer.parseInt(dayBefore.getConfirmed());
				deathsCountryWeek = Integer.parseInt(day.getDeaths()) - Integer.parseInt(dayBefore.getDeaths());
				recoveredCountryWeek = Integer.parseInt(day.getRecovered()) -Integer.parseInt(dayBefore.getRecovered());
				activeCountryWeek = Integer.parseInt(day.getActive()) - Integer.parseInt(dayBefore.getActive());

				CountryDay newDay = new CountryDay(country, confirmedCountryWeek, deathsCountryWeek, recoveredCountryWeek, activeCountryWeek,date1);
				ls.add(newDay);
				log.info(newDay.toString());
				
			}

			int week_cases= 0;
			int week_deaths= 0;
			int week_recovered= 0;
			int week_active= 0;


			for (CountryDay week : ls) {
				week_cases += week.getNewDayCases();
				week_deaths += week.getNewDayDeaths();
				week_recovered += week.getNewDayRecovered();
				week_active += week.getNewDayActive();
			}

			avg_cases = week_cases/ls.size();
			avg_deaths = week_deaths/ls.size();
			avg_recovered = week_recovered/ls.size();
			avg_active = week_active/ls.size();

			// int newCases = Integer.parseInt(list[list.length-2].getConfirmed()) - confirmedCountry;
			// int newDeaths = Integer.parseInt(list[list.length-2].getDeaths()) - deathsCountry;
			// int newRecovered = Integer.parseInt(list[list.length-2].getRecovered()) - recoveredCountry;
			// int newActive= Integer.parseInt(list[list.length-2].getConfirmed()) - activeCountry;

			// CountryDay countryDayStats = new CountryDay(country, newCases, newDeaths, newRecovered, newActive, day);


			

		}catch(Exception e){
			log.info("ERRO ->" + e.toString());
			e.printStackTrace();
			
		}

		//int total = 10;
		log.info(Integer.toString(confirmedCountry));
		log.info(Integer.toString(activeCountry));
		// log.info(ls[0].toString());

		CountryDay currentDay = ls.get(0);
		redirectAttributes.addFlashAttribute("total_cases", confirmedCountry);
        redirectAttributes.addFlashAttribute("deaths", deathsCountry);
		redirectAttributes.addFlashAttribute("recovery", recoveredCountry);
		redirectAttributes.addFlashAttribute("active", activeCountry);
		redirectAttributes.addFlashAttribute("New_total_cases", currentDay.getNewDayCases());
        redirectAttributes.addFlashAttribute("New_deaths", currentDay.getNewDayDeaths());
		redirectAttributes.addFlashAttribute("New_recovery", currentDay.getNewDayRecovered());
		redirectAttributes.addFlashAttribute("New_active", currentDay.getNewDayActive());
		redirectAttributes.addFlashAttribute("avg_cases", avg_cases);
        redirectAttributes.addFlashAttribute("avg_deaths", avg_deaths);
		redirectAttributes.addFlashAttribute("avg_recovery", avg_recovered);
		redirectAttributes.addFlashAttribute("avg_active", avg_active);
		

		return "redirect:/index/";
    }


	@GetMapping("/index")
	public String index(Model model) throws JsonProcessingException{
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.covid19api.com/countries";
		String url2 = "https://api.covid19api.com/summary";
		int confirmed=0;
		int deaths=0;
		int recovered=0;
		int active = 0;
		try{
			String json = restTemplate.getForObject(url, String.class);
			
			ObjectMapper objectMapper = new ObjectMapper();

			Country[] country_list = objectMapper.readValue(json, Country[].class);
					
					
			// log.info("Country Values");
			// for(Country cnt : country_list)
			// 	log.info(cnt.toString());
			// }
			
			model.addAttribute("country_list", country_list);

			//Invenções para tentar meter o global a funfar

			String json2 = restTemplate.getForObject(url2, String.class);
			// log.info(json2);
			
			// log.info(Integer.toString(confirmed));
			Summary summary = new ObjectMapper().readValue(json2, Summary.class) ;
			
			// log.info(global.toString());

			confirmed = Integer.parseInt(summary.getGlobal().getConfirmed());
			deaths = Integer.parseInt(summary.getGlobal().getDeaths());
			recovered = Integer.parseInt(summary.getGlobal().getRecovered());

			log.info(Integer.toString(confirmed));
			
		}catch(Exception e){
			log.info("ERRO ->" + e.toString());
		}

		//Invenções para tentar meter o global a funfar

		// model.addAttribute("total_cases", confirmed);
        // model.addAttribute("deaths", deaths);
		// model.addAttribute("recovery", recovered);
		//model.addAttribute("active", active);
		

		return "index";
	}

	
	
	
	
	@PostMapping("/world")
    public String getWorld() {
		
		
		
		
		return "news";
	}    
	
	
	
	@Scheduled(fixedRate = 60000) // 1min
	public void test(){
		World wrl = new World();
		LocalDateTime dateTime = LocalDateTime.now(); // Gets the current date and time
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String data = dateTime.format(formatter);
        log.info(data);
		
		int confirmed=0;
		int deaths=0;
		int recovered=0;
		
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.covid19api.com/summary";
		try{
			String json = restTemplate.getForObject(url, String.class);

			// log.info(Integer.toString(confirmed));
			Summary summary = new ObjectMapper().readValue(json, Summary.class) ;
			
			// log.info(global.toString());

			confirmed = Integer.parseInt(summary.getGlobal().getConfirmed());
			deaths = Integer.parseInt(summary.getGlobal().getDeaths());
			recovered = Integer.parseInt(summary.getGlobal().getRecovered());

			log.info(Integer.toString(confirmed));
			
		}catch(Exception e){
			log.info("ERRO ->" + e.toString());
		}
		
		
		wrl.setDate(data);
		wrl.setConfirmed(confirmed);
		wrl.setDeaths(deaths);
		wrl.setRecovered(recovered);
		
		worldRepository.save(wrl);







	}

	
	
	
	@GetMapping("/news")
	public String news(Model model) throws JsonProcessingException{
		int confirmed=0;
		int deaths=0;
		int recovered=0;
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.covid19api.com/summary";
		try{
			String json = restTemplate.getForObject(url, String.class);

			// log.info(Integer.toString(confirmed));
			Summary summary = new ObjectMapper().readValue(json, Summary.class) ;
			
			// log.info(global.toString());

			confirmed = Integer.parseInt(summary.getGlobal().getConfirmed());
			deaths = Integer.parseInt(summary.getGlobal().getDeaths());
			recovered = Integer.parseInt(summary.getGlobal().getRecovered());

			log.info(Integer.toString(confirmed));
			
		}catch(Exception e){
			log.info("ERRO ->" + e.toString());
		}
		
		model.addAttribute("total_cases", confirmed);
        model.addAttribute("deaths", deaths);
		model.addAttribute("recovery", recovered);
		
		
		
		return "news";
	}

	@GetMapping("/tables")
	public String tables(Model model) throws JsonProcessingException{
		
		List<World> list = new ArrayList<>();
		worldRepository.findAll().forEach(list::add);
		model.addAttribute("entries_list", list);
		

		return "tables";
	}
	@PostMapping("/tables")
    public String getData(@RequestParam("selDate") String dat) {

        //System.out.println("Date planted: " + dat); //in reality, you'd use a logger instead :)
        return "tables";
    }    






}