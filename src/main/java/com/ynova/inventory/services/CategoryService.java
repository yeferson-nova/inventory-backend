package com.ynova.inventory.services;

import java.util.List;
import java.util.Optional;

import com.ynova.inventory.model.Category;

public interface CategoryService {

	List<Category> listar();

	Optional<Category> porId(Long id);

	Optional<Category> porNombre(String nombre);

	Category guardar(Category category);

	void eliminar(Long id);

	boolean existePorNombre(String nombre);

}
