package jsheargold.spring_data_jpa_bulk_techniques.entitymanager_flushandclear;

import jsheargold.spring_data_jpa_bulk_techniques.common.AbstractCar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Profile("batch")
@Service
public class EnitiyManagerFlushAndClear {

    // Taken from https://frightanic.com/software-development/jpa-batch-inserts/

    @PersistenceContext
    private EntityManager entityManager;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    @Transactional
    public <T extends AbstractCar> Collection<T> loadData(Collection<T> entities) {
        final List<T> savedEntities = new ArrayList<>(entities.size());
        int i = 0;
        for (T t : entities) {
            entityManager.persist(t);
            savedEntities.add(t);
            i++;
            if (i % batchSize == 0) {
                // Flush a batch of inserts and release memory.
                entityManager.flush();
                entityManager.clear();
            }
        }
        return savedEntities;
    }
}
