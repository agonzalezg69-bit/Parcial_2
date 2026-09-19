package com.biblioteca.api.dto;

import com.biblioteca.api.model.EstadoLibro;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LibroRequest {

    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;

    @NotBlank(message = "El autor es obligatorio")
    private String autor;

    @NotBlank(message = "El isbn es obligatorio")
    private String isbn;

    @NotNull(message = "El anio de publicacion es obligatorio")
    @Min(value = 1450, message = "El anio de publicacion no es valido")
    private Integer anioPublicacion;

    @NotNull(message = "El estado es obligatorio")
    private EstadoLibro estado;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public EstadoLibro getEstado() {
        return estado;
    }

    public void setEstado(EstadoLibro estado) {
        this.estado = estado;
    }
}
