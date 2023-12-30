package pl.zdjavapol140.carrental.service.reservationManagement;

import org.springframework.stereotype.Service;
import pl.zdjavapol140.carrental.service.ReservationService;

@Service
public class ReservationHandler {

    private final ReservationService reservationService;
    private final ReservationManagerProvider provider;

    public ReservationHandler(ReservationService reservationService, ReservationManagerProvider provider) {
        this.reservationService = reservationService;
        this.provider = provider;
    }

    public void manageReservations(Long currentReservationId) {

        AdjacentReservationsWrapper wrapper = reservationService.createAdjacentReservationWrapper(currentReservationId);

        provider.findStrategy(wrapper).manageReservations(currentReservationId, wrapper);
    }

}
