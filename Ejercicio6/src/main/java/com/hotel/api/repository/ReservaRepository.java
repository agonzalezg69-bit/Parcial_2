package com.hotel.api.repository;

import com.hotel.api.model.Reserva;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ReservaRepository {

    private final List<Reserva> reservas = new ArrayList<>();
    private final AtomicLong secuenciaId = new AtomicLong(0);

    public Reserva guardar(Reserva reserva) {
        reserva.setId(secuenciaId.incrementAndGet());
        reservas.add(reserva);
        return reserva;
    }

    public List<Reserva> buscarTodos() {
        return new ArrayList<>(reservas);
    }

    public Optional<Reserva> buscarPorId(Long id) {
        return reservas.stream()
                .filter(reserva -> reserva.getId().equals(id))
                .findFirst();
    }

    public List<Reserva> buscarPorHabitacion(String habitacion) {
        return reservas.stream()
                .filter(reserva -> reserva.getHabitacion().equalsIgnoreCase(habitacion))
                .toList();
    }

    public boolean eliminar(Long id) {
        return reservas.removeIf(reserva -> reserva.getId().equals(id));
    }
}
