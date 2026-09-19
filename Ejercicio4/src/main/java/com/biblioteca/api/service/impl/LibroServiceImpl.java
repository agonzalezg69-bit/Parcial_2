package com.biblioteca.api.service.impl;

import com.biblioteca.api.dto.LibroRequest;
import com.biblioteca.api.dto.LibroResponse;
import com.biblioteca.api.exception.DuplicateResourceException;
import com.biblioteca.api.exception.ResourceNotFoundException;
import com.biblioteca.api.model.Libro;
import com.biblioteca.api.repository.LibroRepository;
import com.biblioteca.api.service.LibroService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public LibroResponse registrarLibro(LibroRequest request) {
        libroRepository.buscarPorIsbn(request.getIsbn()).ifPresent(libro -> {
            throw new DuplicateResourceException("Ya existe un libro registrado con el isbn " + request.getIsbn());
        });

        Libro libro = new Libro();
        copiarDatos(request, libro);
        Libro libroGuardado = libroRepository.guardar(libro);
        return convertirAResponse(libroGuardado);
    }

    @Override
    public List<LibroResponse> consultarLibros() {
        return libroRepository.buscarTodos().stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @Override
    public List<LibroResponse> consultarLibrosPorTitulo(String titulo) {
        List<Libro> libros = libroRepository.buscarPorTitulo(titulo);
        if (libros.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron libros con el titulo '" + titulo + "'");
        }
        return libros.stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @Override
    public LibroResponse actualizarLibro(Long id, LibroRequest request) {
        Libro libro = libroRepository.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe un libro con el id " + id));
        copiarDatos(request, libro);
        return convertirAResponse(libro);
    }

    @Override
    public void eliminarLibro(Long id) {
        boolean eliminado = libroRepository.eliminar(id);
        if (!eliminado) {
            throw new ResourceNotFoundException("No existe un libro con el id " + id);
        }
    }

    private void copiarDatos(LibroRequest request, Libro libro) {
        libro.setTitulo(request.getTitulo());
        libro.setAutor(request.getAutor());
        libro.setIsbn(request.getIsbn());
        libro.setAnioPublicacion(request.getAnioPublicacion());
        libro.setEstado(request.getEstado());
    }

    private LibroResponse convertirAResponse(Libro libro) {
        return new LibroResponse(
                libro.getId(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getIsbn(),
                libro.getAnioPublicacion(),
                libro.getEstado());
    }
}
