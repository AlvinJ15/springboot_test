package com.example.apipersons.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.PropertyMapper.Source;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.apipersons.exception.PlanetNotFoundException;
import com.example.apipersons.exception.ResourceNotFoundException;
import com.example.apipersons.model.Person;
import com.example.apipersons.repository.PersonRepository;
import com.example.apipersons.utilities.PersonUtilities;

@RestController
@RequestMapping("/api")
public class PersonController {

	@Autowired
	PersonRepository personRepository;

	// Get All Persons
	@GetMapping("/persons")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Person> getAllPersons() {
		System.out.println("asdada");
		return personRepository.findAll();
	}

	// Create a new Person
	@PostMapping("/persons")
	@CrossOrigin(origins = "http://localhost:4200")
	public Person createPerson(@Valid @RequestBody Person person) {
		if (!PersonUtilities.isValidPlanet(person.getPlanet())) {
			throw new PlanetNotFoundException(person.getPlanet());
		}
		else {
			return personRepository.save(person);
		}
	}

	// Get a Single Person
	@GetMapping("/persons/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Person getPersonById(@PathVariable(value = "id") Long personId) {
		return personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person", "id", personId));
	}

	// Update a Person
	@PutMapping("/persons/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Person updatePerson(@PathVariable(value = "id") Long personId,
			@Valid @RequestBody Person personDetails) {
		Person person = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person", "id", personId));

		if (!PersonUtilities.isValidPlanet(personDetails.getPlanet())) {
			throw new PlanetNotFoundException(personDetails.getPlanet());
		}
		else {
			person.setName(personDetails.getName());
			person.setHeight(personDetails.getHeight());
			person.setMass(personDetails.getMass());
			person.setHairColor(personDetails.getHairColor());
			person.setGender(personDetails.getGender());
			person.setPlanet(personDetails.getPlanet());        

			Person updatedPerson = personRepository.save(person);
			return updatedPerson;
		}
	}

	// Delete a Person
	@DeleteMapping("/persons/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long personId) {
		Person person = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person", "id", personId));

		personRepository.delete(person);

		return ResponseEntity.ok().build();
	}
}