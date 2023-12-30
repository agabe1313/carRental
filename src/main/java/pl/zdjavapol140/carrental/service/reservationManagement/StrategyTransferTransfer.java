package pl.zdjavapol140.carrental.service.reservationManagement;

import org.springframework.stereotype.Component;
import pl.zdjavapol140.carrental.model.Reservation;
import pl.zdjavapol140.carrental.model.ReservationStatus;
import pl.zdjavapol140.carrental.service.ReservationService;

@Component
public class StrategyTransferTransfer implements ReservationManagerStrategy {

    private final ReservationService reservationService;

    public StrategyTransferTransfer(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public boolean isAppropriate(AdjacentReservationsWrapper wrapper) {

        return wrapper.previousReservation().isPresent() &&
                wrapper.previousReservation().get().getStatus().equals(ReservationStatus.TRANSFER) &&
                wrapper.nextReservation().isPresent() &&
                wrapper.nextReservation().get().getStatus().equals(ReservationStatus.TRANSFER);
    }

    @Override
    public void manageReservations(Long currentReservationId, AdjacentReservationsWrapper wrapper) {

        Reservation previousReservation = wrapper.previousReservation().get();
        Reservation nextReservation = wrapper.nextReservation().get();

        if (previousReservation.getPickUpBranchId().equals(nextReservation.getDropOffBranchId())) {
            reservationService.deleteReservationById(nextReservation.getId());

        } else {
            reservationService.updateReservationPickUpBranchId(nextReservation.getId(), previousReservation.getPickUpBranchId());
        }
        reservationService.deleteReservationById(previousReservation.getId());

        reservationService.updateReservationStatus(currentReservationId , ReservationStatus.CANCELED);

    }

}
