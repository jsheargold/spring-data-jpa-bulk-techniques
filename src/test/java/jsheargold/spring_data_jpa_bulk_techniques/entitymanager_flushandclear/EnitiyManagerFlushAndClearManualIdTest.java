package jsheargold.spring_data_jpa_bulk_techniques.entitymanager_flushandclear;

import jsheargold.spring_data_jpa_bulk_techniques.JPABulkLoaderTest;
import jsheargold.spring_data_jpa_bulk_techniques.common.CarManualId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.MILLIS;

@ActiveProfiles("batch")
public class EnitiyManagerFlushAndClearManualIdTest extends JPABulkLoaderTest {

    @Autowired
    private EnitiyManagerFlushAndClear enitiyManagerFlushAndClear;

    public long runTest() {
        List<CarManualId> cars = createCarsManualId();
        LocalDateTime start = LocalDateTime.now();
        enitiyManagerFlushAndClear.loadData(cars);
        LocalDateTime end = LocalDateTime.now();
        return MILLIS.between(start, end);
    }
}
