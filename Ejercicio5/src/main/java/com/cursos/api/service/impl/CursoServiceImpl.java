package com.cursos.api.service.impl;

import com.cursos.api.dto.CursoRequest;
import com.cursos.api.dto.CursoResponse;
import com.cursos.api.exception.DuplicateResourceException;
import com.cursos.api.exception.ResourceNotFoundException;
import com.cursos.api.model.Curso;
import com.cursos.api.repository.CursoRepository;
import com.cursos.api.service.CursoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public CursoResponse crearCurso(CursoRequest request) {
        cursoRepository.buscarPorCodigo(request.getCodigo()).ifPresent(curso -> {
            throw new DuplicateResourceException("Ya existe un curso registrado con el codigo " + request.getCodigo());
        });

        Curso curso = new Curso();
        copiarDatos(request, curso);
        Curso cursoGuardado = cursoRepository.guardar(curso);
        return convertirAResponse(cursoGuardado);
    }

    @Override
    public List<CursoResponse> consultarCursos() {
        return cursoRepository.buscarTodos().stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @Override
    public CursoResponse consultarCursoPorCodigo(String codigo) {
        Curso curso = cursoRepository.buscarPorCodigo(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("No existe un curso con el codigo " + codigo));
        return convertirAResponse(curso);
    }

    @Override
    public CursoResponse actualizarCurso(Long id, CursoRequest request) {
        Curso curso = cursoRepository.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe un curso con el id " + id));
        copiarDatos(request, curso);
        return convertirAResponse(curso);
    }

    @Override
    public void eliminarCurso(Long id) {
        boolean eliminado = cursoRepository.eliminar(id);
        if (!eliminado) {
            throw new ResourceNotFoundException("No existe un curso con el id " + id);
        }
    }

    private void copiarDatos(CursoRequest request, Curso curso) {
        curso.setNombre(request.getNombre());
        curso.setCodigo(request.getCodigo());
        curso.setCreditos(request.getCreditos());
        curso.setEstado(request.getEstado());
    }

    private CursoResponse convertirAResponse(Curso curso) {
        return new CursoResponse(
                curso.getId(),
                curso.getNombre(),
                curso.getCodigo(),
                curso.getCreditos(),
                curso.getEstado());
    }
}
