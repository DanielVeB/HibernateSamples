package com.kurosz.hibernatesamples.repository;

import com.kurosz.hibernatesamples.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
