package roomescape.service;

import org.springframework.stereotype.Service;
import roomescape.entity.ReservationTime;
import roomescape.repository.ReservationTimeRepository;

import java.util.List;

@Service
public class ReservationTimeService {
    private final ReservationTimeRepository reservationTimeRepository;

    public ReservationTimeService(ReservationTimeRepository reservationTimeRepository) {
        this.reservationTimeRepository = reservationTimeRepository;
    }

    public ReservationTime createTime(ReservationTime reservationTime) {
        return reservationTimeRepository.create(reservationTime);
    }

    public List<ReservationTime> readAllTime() {
        return reservationTimeRepository.readAll();
    }

    public void deleteTime(long id) {
        reservationTimeRepository.delete(id);
    }
}
