package com.bender.spring_boot_building_apis.person;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public record Person(
        Integer id,
        @JsonGetter("customName") String name,
        @JsonIgnore int age,
        Gender gender) {}