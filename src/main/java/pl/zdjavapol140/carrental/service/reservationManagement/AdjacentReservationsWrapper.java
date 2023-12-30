package pl.zdjavapol140.carrental.service.reservationManagement;

import pl.zdjavapol140.carrental.model.Reservation;

import java.util.Optional;


public record AdjacentReservationsWrapper(Optional<Reservation> previousReservation, Optional<Reservation> nextReservation) {

}
