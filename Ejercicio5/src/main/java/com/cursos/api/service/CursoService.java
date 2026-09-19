package com.cursos.api.service;

import com.cursos.api.dto.CursoRequest;
import com.cursos.api.dto.CursoResponse;

import java.util.List;

public interface CursoService {

    CursoResponse crearCurso(CursoRequest request);

    List<CursoResponse> consultarCursos();

    CursoResponse consultarCursoPorCodigo(String codigo);

    CursoResponse actualizarCurso(Long id, CursoRequest request);

    void eliminarCurso(Long id);
}
