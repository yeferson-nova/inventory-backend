package com.ynova.inventory.services;

import java.util.List;
import java.util.Optional;

import com.ynova.inventory.model.Category;

public interface ICategoryService {

    List<Category> getAll();

    Optional<Category> findByID(Long id);

    Optional<Category> findByName(String name);

    Category save(Category category);

    void deleteByID(Long id);

    boolean existsByName(String name);

    boolean existsByID(Long id);

}
