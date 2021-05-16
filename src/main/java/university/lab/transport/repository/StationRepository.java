package university.lab.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.lab.transport.entity.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
}
