package roomescape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.dto.CreateReservationRequest;
import roomescape.entity.Reservation;
import roomescape.entity.ReservationTime;
import roomescape.service.ReservationService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> createReservation(@RequestBody CreateReservationRequest createReservationRequest) {
        Reservation reservation = reservationService.createReservation(makeReservationObject(createReservationRequest));

        return ResponseEntity.created(URI.create("/reservations/" + reservation.getId())).body(reservation);
    }

    private Reservation makeReservationObject(CreateReservationRequest createReservationRequest) {
        String name = createReservationRequest.name();
        LocalDate date = createReservationRequest.date();
        long timeId = createReservationRequest.timeId();
        ReservationTime time = new ReservationTime(timeId, null);

        return new Reservation(null, name, date, time);
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> readAllReservations() {
        return ResponseEntity.ok(reservationService.readAllReservation());
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") long reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.noContent().build();
    }
}
