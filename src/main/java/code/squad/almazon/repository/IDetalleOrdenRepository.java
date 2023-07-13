package code.squad.almazon.repository;

import code.squad.almazon.model.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface IDetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer> {
}
