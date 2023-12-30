package pl.zdjavapol140.carrental.service.reservationManagement;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
public class ReservationManagerProvider {

private final List<ReservationManagerStrategy> strategies;

    public ReservationManagerProvider(List<ReservationManagerStrategy> strategies) {
        this.strategies = strategies;
    }

    public ReservationManagerStrategy findStrategy(AdjacentReservationsWrapper adjacentReservationsWrapper) {

        return strategies
                .stream()
                .filter(s -> s.isAppropriate(adjacentReservationsWrapper))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Strategy not found"));
    }
}
