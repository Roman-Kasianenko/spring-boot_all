package com.bender.spring_boot_building_apis.person;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PersonRepository {

    private final AtomicInteger idCounter = new AtomicInteger(0);

    private final List<Person> persons = new ArrayList<>();

    {
        persons.add(new Person(idCounter.incrementAndGet(), "John", 20, Gender.MALE));
        persons.add(new Person(idCounter.incrementAndGet(), "Jain", 23, Gender.FEMALE));
        persons.add(new Person(idCounter.incrementAndGet(), "Bor", 12, Gender.MALE));
    }

    public AtomicInteger getIdCounter() {
        return idCounter;
    }

    public List<Person> getPersons() {
        return persons;
    }
}
