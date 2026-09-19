package com.cursos.api.controller;

import com.cursos.api.dto.CursoRequest;
import com.cursos.api.dto.CursoResponse;
import com.cursos.api.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<CursoResponse> crearCurso(@Valid @RequestBody CursoRequest request) {
        CursoResponse response = cursoService.crearCurso(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CursoResponse>> consultarCursos() {
        return ResponseEntity.ok(cursoService.consultarCursos());
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<CursoResponse> consultarCursoPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(cursoService.consultarCursoPorCodigo(codigo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponse> actualizarCurso(@PathVariable Long id, @Valid @RequestBody CursoRequest request) {
        return ResponseEntity.ok(cursoService.actualizarCurso(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}
