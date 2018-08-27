package com.example.apipersons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.apipersons.model.Person;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface PersonRepository extends JpaRepository<Person, Long> {

}