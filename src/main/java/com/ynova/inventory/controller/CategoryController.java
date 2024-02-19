package com.ynova.inventory.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ynova.inventory.model.Category;
import com.ynova.inventory.services.CategoryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@GetMapping("/categories")
	public List<Category> listar() {
		return service.listar();
	}

	@GetMapping("/categories/{id}")
	public ResponseEntity<?> porId(@PathVariable Long id) {
		Optional<Category> categoryO = service.porId(id);
		if (categoryO.isPresent()) {
			return ResponseEntity.ok().body(categoryO.get());
		}
		return ResponseEntity.notFound().build();

	}

	@GetMapping("{name}")
	public ResponseEntity<?> porNombre(@PathVariable String nombre) {
		Optional<Category> categoryO = service.porNombre(nombre);
		if (categoryO.isPresent()) {
			return ResponseEntity.ok().body(categoryO.get());
		}
		return ResponseEntity.notFound().build();

	}

	@PostMapping
	public ResponseEntity<?> guardar(@Valid @RequestBody Category category, BindingResult result) {
		if (result.hasErrors()) {
			return validacion(result);
		}
		if (!category.getName().isEmpty() && service.existePorNombre(category.getName())) {

			return ResponseEntity.badRequest().body(
					Collections.singletonMap("Mensaje", "ya existe una categoria con el nombre " + category.getName()));

		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(category));
	}

	/*
	 * @DeleteMapping("{id}") public ResponseEntity<?> eliminar
	 */

	private ResponseEntity<?> validacion(BindingResult result) {
		Map<String, String> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}

}
