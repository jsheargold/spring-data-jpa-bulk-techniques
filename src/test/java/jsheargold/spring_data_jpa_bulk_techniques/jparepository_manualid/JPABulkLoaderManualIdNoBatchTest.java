package jsheargold.spring_data_jpa_bulk_techniques.jparepository_manualid;

import jsheargold.spring_data_jpa_bulk_techniques.JPABulkLoaderTest;
import jsheargold.spring_data_jpa_bulk_techniques.common.CarManualId;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.MILLIS;

public class JPABulkLoaderManualIdNoBatchTest extends JPABulkLoaderTest {

    @Autowired
    private JPABulkLoaderManualId jpaBulkLoaderManualId;

    public long runTest() {
        List<CarManualId> cars = createCarsManualId();
        LocalDateTime start = LocalDateTime.now();
        jpaBulkLoaderManualId.loadData(cars);
        LocalDateTime end = LocalDateTime.now();
        return MILLIS.between(start, end);
    }
}
