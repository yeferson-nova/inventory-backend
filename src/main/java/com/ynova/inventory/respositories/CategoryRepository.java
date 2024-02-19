package com.ynova.inventory.respositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ynova.inventory.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Optional<Category> findByName(String name);

	boolean existsByName(String nombre);
}
