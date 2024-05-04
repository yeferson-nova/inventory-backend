package com.ynova.inventory.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ynova.inventory.model.Category;
import com.ynova.inventory.services.ICategoryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.Validator;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    @Autowired
    private ICategoryService service;
    @Autowired
    private Validator validator;

    @GetMapping("/categories")
    public List<Category> listar() {
        return service.getAll();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> porId(@PathVariable Long id) {
        Optional<Category> categoriO = service.findByID(id);
        if (categoriO.isPresent()) {
            return ResponseEntity.ok().body(categoriO.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/categories{name}")
    public ResponseEntity<?> porNombre(@PathVariable String name) {
        Optional<Category> categoriO = service.findByName(name);
        if (categoriO.isPresent()) {
            return ResponseEntity.ok().body(categoriO.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Category category, BindingResult result) {
        if (result.hasErrors()) {
            return validacion(result);
        }
        if (!category.getName().isEmpty() && service.existsByName(category.getName())) {
            return ResponseEntity.badRequest().body(
                    Collections.singletonMap("Message",
                            "ya existe una categoria con el nombre: " + category.getName()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(category));
    }

    @PostMapping("/categories/batch")
    public ResponseEntity<?> crearMasivo(@RequestBody List<Category> categories) {
        List<Category> savedCategories = new ArrayList<>();
        for (Category category : categories) {

            BeanPropertyBindingResult result = new BeanPropertyBindingResult(category, "category");

            validator.validate(category, result);

            if (result.hasErrors()) {
                return validacion(result);
            }

            if (!category.getName().isEmpty() && !service.existsByName(category.getName())) {
                savedCategories.add(service.save(category));
            } else {
                return ResponseEntity.badRequest().body(
                        Collections.singletonMap("Message",
                                "ya existe una categoria con el nombre: " + category.getName()));
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategories);
    }

    @PutMapping("/edit/category/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Category category, BindingResult result,
            @PathVariable Long id) {
        if (result.hasErrors()) {
            return validacion(result);
        }
        if (!category.getName().isEmpty() && service.existsByName(category.getName())) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("Mensaje", "ya existe la categoria " + category.getName()));
        }
        Optional<Category> categoryO = service.findByID(id);
        Category categoryDB = categoryO.get();
        categoryDB.setName(category.getName());
        categoryDB.setDescripcion(category.getDescripcion());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(categoryDB));
    }

    @DeleteMapping("delete/category/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Category> categoryO = service.findByID(id);
        if (categoryO.isPresent()) {
            service.deleteByID(id);
            return ResponseEntity.ok().body("eliminación exitosa");
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validacion(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
