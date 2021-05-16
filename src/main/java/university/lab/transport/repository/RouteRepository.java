package university.lab.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.lab.transport.entity.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
}
