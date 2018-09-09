package jsheargold.spring_data_jpa_bulk_techniques.jparepository_autoid;

import jsheargold.spring_data_jpa_bulk_techniques.common.CarAutoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepositoryAutoId extends JpaRepository<CarAutoId, Integer> {

}
