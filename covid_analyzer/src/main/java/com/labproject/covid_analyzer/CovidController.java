package com.labproject.covid_analyzer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;


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

@Controller
public class CovidController {

	private static final Logger log = LoggerFactory.getLogger(CovidController.class); // A logger, to send output to the log
	
	
	@RequestMapping("/")
	public String root() {
		return "Greetings from Spring Boot!";
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

		model.addAttribute("total_cases", confirmed);
        model.addAttribute("deaths", deaths);
		model.addAttribute("recovery", recovered);
		//model.addAttribute("active", active);

		return "index";
	}

	
	@PostMapping("/index")
    public String showPage(@RequestParam("selCountry") String country,RedirectAttributes redirectAttributes) {

        log.info(country);
		int confirmed=0;
		int deaths=0;
		int recovered=0;
		int active = 0;
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
			confirmed = Integer.parseInt(list[list.length-1].getConfirmed());
			deaths = Integer.parseInt(list[list.length-1].getDeaths());
			recovered = Integer.parseInt(list[list.length-1].getRecovered());
			active = Integer.parseInt(list[list.length-1].getActive());

		}catch(Exception e){
			log.info("ERRO ->" + e.toString());
		}

		//int total = 10;
		redirectAttributes.addFlashAttribute("total_cases", confirmed);
        redirectAttributes.addFlashAttribute("deaths", deaths);
		redirectAttributes.addFlashAttribute("recovery", recovered);
		redirectAttributes.addFlashAttribute("active", active);

		return "redirect:/index";
    }    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/tables")
	public String tables(Model model) throws JsonProcessingException{
		
		

		return "tables";
	}
	@PostMapping("/tables")
    public String getData(@RequestParam("selDate") String dat) {

        System.out.println("Date planted: " + dat); //in reality, you'd use a logger instead :)
        return "tables";
    }    
	
	@GetMapping("/news")
	public String news(Model model) throws JsonProcessingException{
		

		return "news";
	}








}