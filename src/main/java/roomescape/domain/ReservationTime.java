package roomescape.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public class ReservationTime {
    private final Long id;
    @JsonFormat(pattern = "HH:mm")
    private final LocalTime startAt;

    private ReservationTime(Long id, LocalTime startAt) {
        this.id = id;
        this.startAt = startAt;
    }

    public static ReservationTime of(Long id, LocalTime startAt) {
        return new ReservationTime(id, startAt);
    }

    public static ReservationTime withNewId(ReservationTime source, Long id) {
        return new ReservationTime(id, source.startAt);
    }

    public Long getId() {
        return id;
    }

    public LocalTime getStartAt() {
        return startAt;
    }
}
