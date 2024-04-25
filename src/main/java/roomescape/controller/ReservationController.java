package roomescape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationTime;
import roomescape.dto.ReservationRequestDto;
import roomescape.dto.ReservationResponseDto;
import roomescape.dto.ReservationTimeResponseDto;
import roomescape.service.ReservationService;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponseDto> createReservation(@RequestBody ReservationRequestDto reservationRequestDto) {
        Reservation reservation = reservationService.createReservation(makeReservationObject(reservationRequestDto));
        ReservationResponseDto reservationResponseDto = makeReservationResponseDto(reservation);

        return ResponseEntity.created(URI.create("/reservations/" + reservation.getId())).body(reservationResponseDto);
    }

    private Reservation makeReservationObject(ReservationRequestDto reservationRequestDto) {
        String name = reservationRequestDto.name();
        LocalDate date = reservationRequestDto.date();
        Long timeId = reservationRequestDto.timeId();
        ReservationTime reservationTime = new ReservationTime(timeId, null);

        return new Reservation(null, name, date, reservationTime);
    }

    private ReservationResponseDto makeReservationResponseDto(Reservation reservation) {
        Long id = reservation.getId();
        String name = reservation.getName();
        LocalDate date = reservation.getDate();
        Long timeId = reservation.getTime().getId();
        LocalTime startAt = reservation.getTime().getStartAt();
        ReservationTimeResponseDto reservationTimeResponseDto = new ReservationTimeResponseDto(timeId, startAt);

        return new ReservationResponseDto(id, name, date, reservationTimeResponseDto);
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
