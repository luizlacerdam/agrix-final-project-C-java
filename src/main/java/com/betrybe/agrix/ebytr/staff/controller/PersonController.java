package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.PersonDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import com.betrybe.agrix.ebytr.staff.utils.PersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Person controller.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

  private PersonService personService;

  /**
   * Constructor person controller.
   */
  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Rota post para criar novo usuario.
   */
  @PostMapping
  public ResponseEntity<?> createNewPerson(@RequestBody PersonDto personDto) {
    Person newPerson = personService.create(personDto.toPerson());
    PersonResponse personResponse = new PersonResponse(newPerson);
    return ResponseEntity.status(HttpStatus.CREATED).body(personResponse);
  }
}
