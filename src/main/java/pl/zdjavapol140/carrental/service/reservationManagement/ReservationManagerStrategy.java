package pl.zdjavapol140.carrental.service.reservationManagement;

import pl.zdjavapol140.carrental.model.Reservation;

public interface ReservationManagerStrategy {

    boolean isAppropriate(AdjacentReservationsWrapper adjacentReservationsWrapper);

    void manageReservations(Long currentReservationId, AdjacentReservationsWrapper wrapper);

}
