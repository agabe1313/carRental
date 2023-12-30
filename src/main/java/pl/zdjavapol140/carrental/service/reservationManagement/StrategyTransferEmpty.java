package pl.zdjavapol140.carrental.service.reservationManagement;

import org.springframework.stereotype.Component;
import pl.zdjavapol140.carrental.model.ReservationStatus;
import pl.zdjavapol140.carrental.service.ReservationService;

@Component
public class StrategyTransferEmpty implements ReservationManagerStrategy {

    private final ReservationService reservationService;

    public StrategyTransferEmpty(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public boolean isAppropriate(AdjacentReservationsWrapper adjacentReservationsWrapper) {

        return adjacentReservationsWrapper.previousReservation().isPresent() &&
                adjacentReservationsWrapper.previousReservation().get().getStatus().equals(ReservationStatus.TRANSFER) &&
                adjacentReservationsWrapper.nextReservation().isEmpty();
    }

    @Override
    public void manageReservations(Long currentReservationId, AdjacentReservationsWrapper wrapper) {
        if (this.isAppropriate(wrapper)) {
            reservationService.updateReservationStatus(currentReservationId, ReservationStatus.CANCELED);
            reservationService.deleteReservationById(currentReservationId);
        }

    }
}
