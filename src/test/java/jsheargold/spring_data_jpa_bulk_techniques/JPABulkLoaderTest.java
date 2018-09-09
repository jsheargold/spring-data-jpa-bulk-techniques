package jsheargold.spring_data_jpa_bulk_techniques;

import jsheargold.spring_data_jpa_bulk_techniques.common.CarAutoId;
import jsheargold.spring_data_jpa_bulk_techniques.common.CarManualId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class JPABulkLoaderTest {

    @Value("${NUMBER_OF_RUNS}")
    private final int NUMBER_OF_RUNS = 5;
    @Value("${TEST_SAMPLE_SIZE}")
    private final int TEST_SAMPLE_SIZE = 1_000;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void cleanUp() {
        truncate();
    }

    private void truncate() {
        List<String> tableNames = new ArrayList<>();
        tableNames.add("car_auto_id");
        tableNames.add("car_manual_id");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.flush();
        tableNames.forEach(tableName -> entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate());
        entityManager.clear();
        tx.commit();
    }

    protected List<CarManualId> createCarsManualId() {
        List<CarManualId> cars = new ArrayList<>();
        IntStream.range(0, TEST_SAMPLE_SIZE).forEach((i) ->
            cars.add(new CarManualId("Ford", "Mondeo", "AB12 CDE", 2000, "Red", 2000.50)));
        return cars;
    }

    protected List<CarAutoId> createCarsAutoId() {
        List<CarAutoId> cars = new ArrayList<>();
        IntStream.range(0, TEST_SAMPLE_SIZE).forEach((i) ->
            cars.add(new CarAutoId("Ford", "Mondeo", "AB12 CDE", 2000, "Red", 2000.50)));
        return cars;
    }

    private void printMemoryStats() {
        int mb = 1024 * 1024;
        Runtime instance = Runtime.getRuntime();
        long totalMemory = instance.totalMemory() / mb;
        long freeMemory = instance.freeMemory() / mb;
        long maxMemory = instance.maxMemory() / mb;
        long usedMemory = (instance.totalMemory() - instance.freeMemory()) / mb;
        System.out.println("- totalMemory: " + totalMemory + "MB" +
                " freeMemory: " + freeMemory + "MB" +
                " usedMemory: " + usedMemory + "MB" +
                " maxMemory: " + maxMemory + "MB");
    }

    public abstract long runTest();

    @Test
    public void loadData_test() {
        System.out.println("Pre-run stats");
        printMemoryStats();
        AtomicLong totalTime = new AtomicLong();
        IntStream.range(0, NUMBER_OF_RUNS).forEach((i) -> {
            System.out.println("Run " + (i + 1) + " stats");
            long timeTaken = runTest();
            printMemoryStats();
            System.out.println("- Time taken " + timeTaken + " ms");
            totalTime.addAndGet(timeTaken);
        });
        System.out.println("Average time " + totalTime.get() / (NUMBER_OF_RUNS) + " ms");
    }
}
