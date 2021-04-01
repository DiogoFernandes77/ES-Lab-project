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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;



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
		String json = restTemplate.getForObject(url, String.class);
		//String json = "[{\"Country\":\"mkyong\", \"ISO2\":\"ola\"}, {\"Country\":\"fong\", \"ISO2\":\"38\"}]";
		ObjectMapper objectMapper = new ObjectMapper();
		
		// HashMap<String, Country> country_map = new HashMap<String, Country>();
		try{

			Country[] country_list = objectMapper.readValue(json, Country[].class);
					
					
					log.info("Country Values");
					for(Country cnt : country_list){
						System.out.print(cnt);
					}
			model.addAttribute("country_list", country_list);

		}catch(Exception e){
			log.info("ERRO");
		}
		
		
		

        
		
		return "index";
		
		
		
	}

}