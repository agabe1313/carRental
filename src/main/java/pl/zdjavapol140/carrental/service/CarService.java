package pl.zdjavapol140.carrental.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zdjavapol140.carrental.model.Car;
import pl.zdjavapol140.carrental.model.CarStatus;
import pl.zdjavapol140.carrental.model.Reservation;
import pl.zdjavapol140.carrental.repository.CarRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CarService {

    CarRepository carRepository;

    @Transactional
    public void addCar(Car car) {
        carRepository.save(car);
    }

    @Transactional
    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
    }

    /* Metoda wyszukuje samochody dostępne w poszukiwanym terminie we wszystkich lokalizacjach.

     */

//public List<Car> findAllAvailableCars(List<Car> carsUnavailableOnCurrentDateTime)

//    public List<Car> findAvailableCarsByConflictingReservations(List<Reservation> conflictingReservations) {
//
//        List<Car> nonAvailableCars = conflictingReservations
//                .stream()
//                .map(Reservation::getCar)
//                .distinct()
//                .toList();
//
//        return carRepository
//                .findAll()
//                .stream()
//                .filter(car -> !nonAvailableCars.contains(car))
//                .toList();
//    }

    /* Metoda wyszukuje id lokalizacji, w której będzie dostępny samochód w poszukiwanym terminie.

     */
//    public Long findBranchIdByCarDropOffDateTimeBefore(LocalDateTime currentPickUpDateTime, Car car) {
//
//        return car.getReservations()
//                .stream()
//                .filter(reservation -> reservation.getDropOffDateTime().isBefore(currentPickUpDateTime))
//                .max(Comparator.comparing(Reservation::getDropOffDateTime))
//                .map(Reservation::getDropOffBranchId)
//                .orElseGet(() -> car.getBranch().getId());
//    }

    /* Metoda wyszukuje samochody dostępne w wybranej lokalizacji w wybranym terminie.

     */

//    public List<Car> findAvailableCarsByCarDropOffBranchId(List<Car> cars, Long currentPickUpBranchId, LocalDateTime currentPickUpDateTime) {
//        return cars
//                .stream()
//                .filter(car -> findBranchIdByCarDropOffDateTimeBefore(currentPickUpDateTime, car).equals(currentPickUpBranchId))
//                .collect(Collectors.toList());
//    }



    //TODO
    //Zapewnić, żeby auto wracało na miejsce - tam, gdzie jest następna rezerwacja.
    //Poprzenosić metody z serwisów do fasady










    public List<Car> findCarsByBodyType(String bodyType) {
        return carRepository.findCarsByBodyType(bodyType);
    }

    public List<Car> findCarsByPriceLessThanEqual(List<Car> cars, BigDecimal priceLimit) {
        return cars
                .stream()
                .filter(car -> car.getPrice().compareTo(priceLimit) <= 0)
                .collect(Collectors.toList());
    }

    public List<Car> findCarsByPriceLessThanEqual(BigDecimal priceLimit) {
        return carRepository.findCarsByPriceIsLessThanEqual(priceLimit);
    }

//    public List<Car> findCarsByBrand(List<Car> cars, String brand) {
//        return cars
//                .stream()
//                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
//                .collect(Collectors.toList());
//    }
//    public List<Car> findCarsByBrand(String brand) {
//        return carRepository.findCarsByBrand(brand);
//    }
//
//    public List<Car> findCarsByModel(List<Car> cars, String model) {
//        return cars
//                .stream()
//                .filter(car -> car.getModel().equalsIgnoreCase(model))
//                .collect(Collectors.toList());
//    }
//
//    public List<Car> findCarsByModel(String model) {
//        return carRepository.findCarsByModel(model);
//    }
//    public List<Car> findCarsByColor(List<Car> cars, String color) {
//        return cars
//                .stream()
//                .filter(car -> car.getColor().equalsIgnoreCase(color))
//                .collect(Collectors.toList());
//    }
//
//    public List<Car> findCarsByColor(String color) {
//        return carRepository.findCarsByColor(color);
//    }

//    //TODO
//    public void updateCarStatusToRented(Long carId) {
//        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car id not found"));
//        car.setStatus(CarStatus.RENTED);
//        carRepository.save(car);
//    }
}
