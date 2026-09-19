package com.biblioteca.api.controller;

import com.biblioteca.api.dto.LibroRequest;
import com.biblioteca.api.dto.LibroResponse;
import com.biblioteca.api.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public ResponseEntity<LibroResponse> registrarLibro(@Valid @RequestBody LibroRequest request) {
        LibroResponse response = libroService.registrarLibro(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<LibroResponse>> consultarLibros() {
        return ResponseEntity.ok(libroService.consultarLibros());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<LibroResponse>> consultarLibroPorTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(libroService.consultarLibrosPorTitulo(titulo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibroResponse> actualizarLibro(@PathVariable Long id, @Valid @RequestBody LibroRequest request) {
        return ResponseEntity.ok(libroService.actualizarLibro(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }
}
