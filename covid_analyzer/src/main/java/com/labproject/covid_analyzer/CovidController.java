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
		try{
			String json = restTemplate.getForObject(url, String.class);
			
			ObjectMapper objectMapper = new ObjectMapper();

			Country[] country_list = objectMapper.readValue(json, Country[].class);
					
					
			// log.info("Country Values");
			// for(Country cnt : country_list){
			// 	log.info(cnt.toString());
			// }
			
			model.addAttribute("country_list", country_list);
			
		}catch(Exception e){
			log.info("ERRO ->" + e.toString());
		}

		return "index";
	}

	
	@PostMapping("/index")
    public String showPage(@RequestParam("selCountry") String country,RedirectAttributes redirectAttributes) {

        log.info(country);
		//basicamente é ir buscar agr os stats para o pais em questao
		int total = 10;
		redirectAttributes.addFlashAttribute("total_cases", total);
        //redirectAttributes.addFlashAttribute("deaths", total);
		//redirectAttributes.addFlashAttribute("recovery", total);
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