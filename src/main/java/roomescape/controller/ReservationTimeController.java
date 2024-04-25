package roomescape.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.dto.ReservationTimeRequestDto;
import roomescape.dto.ReservationTimeResponseDto;
import roomescape.entity.ReservationTime;
import roomescape.service.ReservationTimeService;

import java.time.LocalTime;
import java.util.List;

@RestController
public class ReservationTimeController {
    private final ReservationTimeService reservationTimeService;

    public ReservationTimeController(ReservationTimeService reservationTimeService) {
        this.reservationTimeService = reservationTimeService;
    }

    @PostMapping("/times")
    public ResponseEntity<ReservationTimeResponseDto> createTime(@RequestBody ReservationTimeRequestDto reservationTimeRequestDto) {
        ReservationTime reservationTime = reservationTimeService.createTime(makeTimeObject(reservationTimeRequestDto));
        ReservationTimeResponseDto reservationTimeResponseDto = makeTimeResponseDto(reservationTime);

        return ResponseEntity.status(HttpStatus.CREATED).body(reservationTimeResponseDto);
    }

    private ReservationTime makeTimeObject(ReservationTimeRequestDto reservationTimeRequestDto) {
        LocalTime startAt = reservationTimeRequestDto.startAt();

        return new ReservationTime(null, startAt);
    }

    private ReservationTimeResponseDto makeTimeResponseDto(ReservationTime reservationTime) {
        Long id = reservationTime.getId();
        LocalTime startAt = reservationTime.getStartAt();

        return new ReservationTimeResponseDto(id, startAt);
    }

    @GetMapping("/times")
    public ResponseEntity<List<ReservationTime>> readAllTime() {
        return ResponseEntity.ok(reservationTimeService.readAllTime());
    }

    @DeleteMapping("/times/{id}")
    public ResponseEntity<Void> deleteTime(@PathVariable("id") long timeId) {
        reservationTimeService.deleteTime(timeId);
        return ResponseEntity.noContent().build();
    }
}
