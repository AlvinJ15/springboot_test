package com.example.apipersons.utilities;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PersonUtilities {

	public static final String PLANET_URI = "https://swapi.co/api/people/?search=";
	public static HttpHeaders httpHeaders;

	public static boolean isValidPlanet(String planet) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response; 

		ObjectMapper mapper = new ObjectMapper();
		JsonNode root;
		try {
			int count;
			String next = PLANET_URI + planet;
			JsonNode results;

			do {
				HttpEntity<String> entity = new HttpEntity<String>("parameters", getHttpHeaders());
				response = restTemplate.exchange(next, HttpMethod.GET,entity,String.class);
				root = mapper.readTree(response.getBody());

				count = root.path("count").asInt();
				
				if(count == 0) {
					return false;
				}
				
				next = root.path("next").asText("");
				results = root.path("results");

				int i = 0;
				JsonNode jsonPlanet;

				do {
					jsonPlanet = results.get(++i);

					if (jsonPlanet == null) {
						break;
					}
					else {
						if (jsonPlanet.path("name").asText().equals(planet)) {
							return true;
						}
					}
				}
				while (jsonPlanet != null);
			}
			while (next != null && !next.isEmpty());

			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	private static HttpHeaders getHttpHeaders() {
		if (httpHeaders == null) {
			httpHeaders = new HttpHeaders();
			httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			httpHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		}

		return httpHeaders;
	}
}
