package jsheargold.spring_data_jpa_bulk_techniques.jparepository_manualid;

import jsheargold.spring_data_jpa_bulk_techniques.common.CarManualId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepositoryManualId extends JpaRepository<CarManualId, Integer> {

}
