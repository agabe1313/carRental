package pl.zdjavapol140.carrental.service.reservationManagement;

import org.springframework.stereotype.Component;
import pl.zdjavapol140.carrental.model.Reservation;
import pl.zdjavapol140.carrental.model.ReservationStatus;
import pl.zdjavapol140.carrental.service.ReservationService;

@Component
public class StrategyEmptyEmpty implements ReservationManagerStrategy {

    private final ReservationService reservationService;

    public StrategyEmptyEmpty(ReservationService reservationService) {
        this.reservationService = reservationService;

    }

    @Override
    public boolean isAppropriate(AdjacentReservationsWrapper adjacentReservationsWrapper) {
        return adjacentReservationsWrapper.previousReservation().isEmpty() && adjacentReservationsWrapper.nextReservation().isEmpty();
    }

    @Override
    public void manageReservations(Long currentReservationId, AdjacentReservationsWrapper wrapper) {
        if (this.isAppropriate(reservationService.createAdjacentReservationWrapper(currentReservationId))) {

            reservationService.updateReservationStatus(currentReservationId, ReservationStatus.CANCELED);
        }
    }
}
