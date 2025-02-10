package com.bender.spring_boot_building_apis.person;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getPersons(
            @RequestParam(
                    value = "sort",
                    required = false,
                    defaultValue = "DESC"
            ) SortingOrder sort,
            @RequestParam(
                    value = "limit",
                    required = false,
                    defaultValue = "10"
            ) Integer limit
    ) {
        return personService.getPersons(sort, limit);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Person>> getPersonById(
            @PathVariable("id") Integer id
    ) {
        Optional<Person> person = personService.getPersonById(id);
        return ResponseEntity.ok().body(person);
    }

    @DeleteMapping("{id}")
    public void deletePersonById(@PathVariable("id") Integer id) {
        personService.deletePersonById(id);
    }

    @PostMapping
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @PutMapping("{id}")
    public void updatePersonById(@RequestBody Person updPerson, @PathVariable Integer id) {
        personService.updatePersonById(updPerson, id);
    }
}
