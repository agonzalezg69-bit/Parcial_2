package com.hotel.api.controller;

import com.hotel.api.dto.ReservaRequest;
import com.hotel.api.dto.ReservaResponse;
import com.hotel.api.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<ReservaResponse> crearReserva(@Valid @RequestBody ReservaRequest request) {
        ReservaResponse response = reservaService.crearReserva(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ReservaResponse>> consultarReservas() {
        return ResponseEntity.ok(reservaService.consultarReservas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponse> consultarReservaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.consultarReservaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaResponse> actualizarReserva(@PathVariable Long id, @Valid @RequestBody ReservaRequest request) {
        return ResponseEntity.ok(reservaService.actualizarReserva(id, request));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ReservaResponse> cancelarReserva(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.cancelarReserva(id));
    }
}
