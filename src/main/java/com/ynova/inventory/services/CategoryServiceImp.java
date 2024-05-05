package com.ynova.inventory.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynova.inventory.model.Category;
import com.ynova.inventory.repositories.CategoryRepository;

@Service
public class CategoryServiceImp implements ICategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAll() {
        return (List<Category>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findByID(Long id) {

        return repository.findById(id);
    }

    @Override
    public List<Category> findByStatus(boolean status) {

        return (List<Category>) repository.findByStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findByName(String name) {

        return repository.findByName(name);
    }

    @Override
    @Transactional
    public Category save(Category category) {

        return repository.save(category);
    }

    @Override
    @Transactional
    public void deleteByID(Long id) {

        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {

        return repository.existsByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByID(Long id) {

        return repository.existsById(id);
    }

}
