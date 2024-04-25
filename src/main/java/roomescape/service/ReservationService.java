package roomescape.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roomescape.domain.Reservation;
import roomescape.repository.ReservationRepository;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservation(Reservation reservation) {
        Reservation createdReservation = reservationRepository.create(reservation);

        return readReservation(createdReservation.getId());
    }

    public Reservation readReservation(Long id) {
        return reservationRepository.read(id);
    }

    public List<Reservation> readAllReservation() {
        return reservationRepository.readAll();
    }

    public void deleteReservation(long reservationId) {
        reservationRepository.delete(reservationId);
    }
}
