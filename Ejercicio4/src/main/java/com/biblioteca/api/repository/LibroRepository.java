package com.biblioteca.api.repository;

import com.biblioteca.api.model.Libro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LibroRepository {

    private final List<Libro> libros = new ArrayList<>();
    private final AtomicLong secuenciaId = new AtomicLong(0);

    public Libro guardar(Libro libro) {
        libro.setId(secuenciaId.incrementAndGet());
        libros.add(libro);
        return libro;
    }

    public List<Libro> buscarTodos() {
        return new ArrayList<>(libros);
    }

    public Optional<Libro> buscarPorId(Long id) {
        return libros.stream()
                .filter(libro -> libro.getId().equals(id))
                .findFirst();
    }

    public List<Libro> buscarPorTitulo(String titulo) {
        String busqueda = titulo.toLowerCase();
        return libros.stream()
                .filter(libro -> libro.getTitulo().toLowerCase().contains(busqueda))
                .toList();
    }

    public Optional<Libro> buscarPorIsbn(String isbn) {
        return libros.stream()
                .filter(libro -> libro.getIsbn().equalsIgnoreCase(isbn))
                .findFirst();
    }

    public boolean eliminar(Long id) {
        return libros.removeIf(libro -> libro.getId().equals(id));
    }
}
