package jsheargold.spring_data_jpa_bulk_techniques.jparepository_manualid;

public class IdGenerator {

    private static int id = 0;

    public static int getNextId() {
        return ++id;
    }
}
