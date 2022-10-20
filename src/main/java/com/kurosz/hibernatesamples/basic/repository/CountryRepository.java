package com.kurosz.hibernatesamples.basic.repository;

import com.kurosz.hibernatesamples.basic.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
