package pl.zdjavapol140.carrental.service.reservationManagement;

import org.springframework.stereotype.Component;
import pl.zdjavapol140.carrental.model.ReservationStatus;
import pl.zdjavapol140.carrental.service.ReservationService;

@Component
public class StrategyNontransferNontransfer implements ReservationManagerStrategy {

    private final ReservationService reservationService;

    public StrategyNontransferNontransfer(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public boolean isAppropriate(AdjacentReservationsWrapper adjacentReservationsWrapper) {

        return adjacentReservationsWrapper.previousReservation().isPresent() &&
                !adjacentReservationsWrapper.previousReservation().get().getStatus().equals(ReservationStatus.TRANSFER) &&
                adjacentReservationsWrapper.nextReservation().isPresent() &&
                !adjacentReservationsWrapper.nextReservation().get().getStatus().equals(ReservationStatus.TRANSFER);
    }

    @Override
    public void manageReservations(Long currentReservationId, AdjacentReservationsWrapper wrapper) {

    }
}
