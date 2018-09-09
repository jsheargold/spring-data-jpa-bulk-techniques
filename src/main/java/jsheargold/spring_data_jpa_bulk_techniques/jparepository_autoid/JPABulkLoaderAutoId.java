package jsheargold.spring_data_jpa_bulk_techniques.jparepository_autoid;

import jsheargold.spring_data_jpa_bulk_techniques.common.CarAutoId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class JPABulkLoaderAutoId {

    @Autowired
    private CarRepositoryAutoId carRepositoryAutoId;

    @Transactional
    public void loadData(Collection<CarAutoId> cars) {
        carRepositoryAutoId.save(cars);
    }
}
