package jsheargold.spring_data_jpa_bulk_techniques.jparepository_manualid;

import jsheargold.spring_data_jpa_bulk_techniques.common.CarManualId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class JPABulkLoaderManualId {

    @Autowired
    private CarRepositoryManualId carRepositoryManualId;

    @Transactional
    public void loadData(Collection<CarManualId> cars) {
        carRepositoryManualId.save(cars);
    }
}
