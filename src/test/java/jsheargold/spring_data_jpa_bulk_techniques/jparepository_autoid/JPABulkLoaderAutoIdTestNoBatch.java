package jsheargold.spring_data_jpa_bulk_techniques.jparepository_autoid;

import jsheargold.spring_data_jpa_bulk_techniques.common.CarAutoId;
import jsheargold.spring_data_jpa_bulk_techniques.JPABulkLoaderTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.MILLIS;

public class JPABulkLoaderAutoIdTestNoBatch extends JPABulkLoaderTest {

    @Autowired
    private JPABulkLoaderAutoId jpaBulkLoaderAutoId;

    public long runTest() {
        List<CarAutoId> cars = createCarsAutoId();
        LocalDateTime start = LocalDateTime.now();
        jpaBulkLoaderAutoId.loadData(cars);
        LocalDateTime end = LocalDateTime.now();
        return MILLIS.between(start, end);
    }
}
