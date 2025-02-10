package com.bender.spring_boot_building_apis.person;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons(
            SortingOrder sort,
            Integer limit
    ) {
        List<Person> persons = personRepository.getPersons();
        if (sort == SortingOrder.ASC) {
            return persons.stream()
                    .sorted(Comparator.comparing(Person::id))
                    .limit(Long.valueOf(limit))
                    .collect(Collectors.toList());
        }
        return persons.stream()
                .sorted(Comparator.comparing(Person::id).reversed())
                .limit(Long.valueOf(limit))
                .collect(Collectors.toList());
    }

    public Optional<Person> getPersonById(Integer id) {
        return personRepository.getPersons().stream().filter(p -> p.id() == id).findFirst();
    }

    public void deletePersonById(Integer id) {
        personRepository.getPersons().removeIf(p -> p.id() == id);
    }

    public void addPerson(Person person) {
        personRepository.getPersons().add(
                new Person(
                        personRepository.getIdCounter().incrementAndGet(),
                        person.name(),
                        person.age(),
                        person.gender())
        );
    }

    public void updatePersonById(Person updPerson, Integer id) {
        personRepository.getPersons().removeIf(p -> p.id() == id);
        personRepository.getPersons().add(
                new Person(
                        id,
                        updPerson.name(),
                        updPerson.age(),
                        updPerson.gender())
        );
    }
}
