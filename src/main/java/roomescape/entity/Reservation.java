package roomescape.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    // id, name, date, time
    private final long id;
    private final String name;
    private final LocalDate date;
    private final LocalTime time;

    public Reservation(long id, String name, LocalDate date, LocalTime time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
}