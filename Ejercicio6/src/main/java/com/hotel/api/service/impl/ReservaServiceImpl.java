package com.hotel.api.service.impl;

import com.hotel.api.dto.ReservaRequest;
import com.hotel.api.dto.ReservaResponse;
import com.hotel.api.exception.ConflictException;
import com.hotel.api.exception.InvalidRequestException;
import com.hotel.api.exception.ResourceNotFoundException;
import com.hotel.api.model.EstadoReserva;
import com.hotel.api.model.Reserva;
import com.hotel.api.repository.ReservaRepository;
import com.hotel.api.service.ReservaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public ReservaResponse crearReserva(ReservaRequest request) {
        validarFechas(request);
        validarDisponibilidad(request, null);

        Reserva reserva = new Reserva();
        copiarDatos(request, reserva);
        Reserva reservaGuardada = reservaRepository.guardar(reserva);
        return convertirAResponse(reservaGuardada);
    }

    @Override
    public List<ReservaResponse> consultarReservas() {
        return reservaRepository.buscarTodos().stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @Override
    public ReservaResponse consultarReservaPorId(Long id) {
        Reserva reserva = obtenerReservaOLanzarExcepcion(id);
        return convertirAResponse(reserva);
    }

    @Override
    public ReservaResponse actualizarReserva(Long id, ReservaRequest request) {
        Reserva reserva = obtenerReservaOLanzarExcepcion(id);
        validarFechas(request);
        validarDisponibilidad(request, id);
        copiarDatos(request, reserva);
        return convertirAResponse(reserva);
    }

    @Override
    public ReservaResponse cancelarReserva(Long id) {
        Reserva reserva = obtenerReservaOLanzarExcepcion(id);
        if (reserva.getEstado() == EstadoReserva.CANCELADA) {
            throw new ConflictException("La reserva con id " + id + " ya se encuentra cancelada");
        }
        reserva.setEstado(EstadoReserva.CANCELADA);
        return convertirAResponse(reserva);
    }

    private Reserva obtenerReservaOLanzarExcepcion(Long id) {
        return reservaRepository.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe una reserva con el id " + id));
    }

    private void validarFechas(ReservaRequest request) {
        if (!request.getFechaSalida().isAfter(request.getFechaEntrada())) {
            throw new InvalidRequestException("La fecha de salida debe ser posterior a la fecha de entrada");
        }
    }

    private void validarDisponibilidad(ReservaRequest request, Long idExcluido) {
        boolean seSolapan = reservaRepository.buscarPorHabitacion(request.getHabitacion()).stream()
                .filter(reserva -> !reserva.getId().equals(idExcluido))
                .filter(reserva -> reserva.getEstado() != EstadoReserva.CANCELADA)
                .anyMatch(reserva -> request.getFechaEntrada().isBefore(reserva.getFechaSalida())
                        && reserva.getFechaEntrada().isBefore(request.getFechaSalida()));
        if (seSolapan) {
            throw new ConflictException("La habitacion " + request.getHabitacion() + " ya esta reservada en las fechas indicadas");
        }
    }

    private void copiarDatos(ReservaRequest request, Reserva reserva) {
        reserva.setNombreCliente(request.getNombreCliente());
        reserva.setHabitacion(request.getHabitacion());
        reserva.setFechaEntrada(request.getFechaEntrada());
        reserva.setFechaSalida(request.getFechaSalida());
        reserva.setEstado(request.getEstado());
    }

    private ReservaResponse convertirAResponse(Reserva reserva) {
        return new ReservaResponse(
                reserva.getId(),
                reserva.getNombreCliente(),
                reserva.getHabitacion(),
                reserva.getFechaEntrada(),
                reserva.getFechaSalida(),
                reserva.getEstado());
    }
}
