package com.cursos.api.dto;

import com.cursos.api.model.EstadoCurso;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CursoRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El codigo es obligatorio")
    private String codigo;

    @NotNull(message = "Los creditos son obligatorios")
    @Min(value = 1, message = "Los creditos deben ser al menos 1")
    private Integer creditos;

    @NotNull(message = "El estado es obligatorio")
    private EstadoCurso estado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public EstadoCurso getEstado() {
        return estado;
    }

    public void setEstado(EstadoCurso estado) {
        this.estado = estado;
    }
}
