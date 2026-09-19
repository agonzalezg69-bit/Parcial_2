package com.hotel.api.service;

import com.hotel.api.dto.ReservaRequest;
import com.hotel.api.dto.ReservaResponse;

import java.util.List;

public interface ReservaService {

    ReservaResponse crearReserva(ReservaRequest request);

    List<ReservaResponse> consultarReservas();

    ReservaResponse consultarReservaPorId(Long id);

    ReservaResponse actualizarReserva(Long id, ReservaRequest request);

    ReservaResponse cancelarReserva(Long id);
}
