package com.ynova.inventory.repositories;

import java.util.Optional;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.ynova.inventory.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByName(String name);

    boolean existsByName(String name);

    List<Category> findByStatus(boolean status);

}