package com.labproject.covid_analyzer;

import org.springframework.data.repository.CrudRepository;

import com.labproject.covid_analyzer.World;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface WorldRepository extends CrudRepository<World, String> {

}