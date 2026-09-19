package com.biblioteca.api.service;

import com.biblioteca.api.dto.LibroRequest;
import com.biblioteca.api.dto.LibroResponse;

import java.util.List;

public interface LibroService {

    LibroResponse registrarLibro(LibroRequest request);

    List<LibroResponse> consultarLibros();

    List<LibroResponse> consultarLibrosPorTitulo(String titulo);

    LibroResponse actualizarLibro(Long id, LibroRequest request);

    void eliminarLibro(Long id);
}
