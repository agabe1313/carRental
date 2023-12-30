package pl.zdjavapol140.carrental.service.reservationManagement;

import org.springframework.stereotype.Component;
import pl.zdjavapol140.carrental.model.Reservation;
import pl.zdjavapol140.carrental.model.ReservationStatus;
import pl.zdjavapol140.carrental.service.ReservationService;

@Component
public class StrategyTransferNontransfer implements ReservationManagerStrategy {

    private final ReservationService reservationService;

    public StrategyTransferNontransfer(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public boolean isAppropriate(AdjacentReservationsWrapper adjacentReservationsWrapper) {
        return adjacentReservationsWrapper.previousReservation().isPresent() &&
                adjacentReservationsWrapper.previousReservation().get().getStatus().equals(ReservationStatus.TRANSFER) &&
                adjacentReservationsWrapper.nextReservation().isPresent() &&
                !adjacentReservationsWrapper.nextReservation().get().getStatus().equals(ReservationStatus.TRANSFER);
    }

    @Override
    public void manageReservations(Long currentReservationId, AdjacentReservationsWrapper wrapper) {

        Reservation previous = wrapper.previousReservation().get();
        Reservation next = wrapper.nextReservation().get();

        if (previous.getPickUpBranchId().equals(next.getPickUpBranchId())) {

            reservationService.deleteReservationById(previous.getId());

        } else {
            previous.setPickUpDateTime(next.getPickUpDateTime().minusHours(8L));
            previous.setDropOffDateTime(next.getPickUpDateTime().minusHours(2L));

            if (!previous.getDropOffBranchId().equals(next.getPickUpBranchId())) {

                previous.setDropOffBranchId(next.getPickUpBranchId());
            }
            reservationService.updateReservation(previous);
        }

        reservationService.updateReservationStatus(currentReservationId, ReservationStatus.CANCELED);

    }

}
