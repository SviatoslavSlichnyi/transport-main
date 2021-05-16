package university.lab.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.lab.transport.entity.PublicTransport;

@Repository
public interface PublicTransportRepository extends JpaRepository<PublicTransport, Long> {
}
