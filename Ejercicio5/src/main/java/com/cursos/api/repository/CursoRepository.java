package com.cursos.api.repository;

import com.cursos.api.model.Curso;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CursoRepository {

    private final List<Curso> cursos = new ArrayList<>();
    private final AtomicLong secuenciaId = new AtomicLong(0);

    public Curso guardar(Curso curso) {
        curso.setId(secuenciaId.incrementAndGet());
        cursos.add(curso);
        return curso;
    }

    public List<Curso> buscarTodos() {
        return new ArrayList<>(cursos);
    }

    public Optional<Curso> buscarPorId(Long id) {
        return cursos.stream()
                .filter(curso -> curso.getId().equals(id))
                .findFirst();
    }

    public Optional<Curso> buscarPorCodigo(String codigo) {
        return cursos.stream()
                .filter(curso -> curso.getCodigo().equalsIgnoreCase(codigo))
                .findFirst();
    }

    public boolean eliminar(Long id) {
        return cursos.removeIf(curso -> curso.getId().equals(id));
    }
}
