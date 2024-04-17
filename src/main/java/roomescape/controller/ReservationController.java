package roomescape.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import roomescape.controller.dto.ReservationDto;
import roomescape.entity.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@RequestMapping("/reservations")
@Controller
public class ReservationController {

    private List<Reservation> reservations = new ArrayList<>();
    private final AtomicLong index = new AtomicLong(1);

    @GetMapping()
    @ResponseBody
    public List<Reservation> readAllReservations() {
        return reservations;
    }

    @PostMapping()
    @ResponseBody
    public Reservation createReservation(@RequestBody ReservationDto reservationDto) {
        long id = index.getAndIncrement();
        String name = reservationDto.name();
        LocalDate date = reservationDto.date();
        LocalTime time = reservationDto.time();

        Reservation newReservation = new Reservation(id, name, date, time);
        reservations.add(newReservation);
        return newReservation;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservationById(@PathVariable("id") long id) {
        Reservation findReservation = reservations.stream()
                .filter(reservation -> reservation.getId() == id)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("id에 해당하는 예약을 찾을 수 없습니다: " + id));
        reservations.remove(findReservation);
        return ResponseEntity.ok().build();
    }
}
