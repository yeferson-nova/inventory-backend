package com.ynova.inventory.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynova.inventory.model.Category;
import com.ynova.inventory.respositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Category> listar() {
		return (List<Category>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Category> porId(Long id) {
		return repository.findById(id);
	}

	@Override
	public boolean existePorNombre(String nombre) {
		return repository.existsByName(nombre);
	}

	@Override
	@Transactional
	public Optional<Category> porNombre(String nombre) {
		return repository.findByName(nombre);
	}

	@Override
	@Transactional
	public Category guardar(Category category) {
		return repository.save(category);
	}

	@Override
	@Transactional
	public void eliminar(Long id) {
		repository.deleteById(id);

	}

}
